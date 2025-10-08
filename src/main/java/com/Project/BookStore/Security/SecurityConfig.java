package com.Project.BookStore.Security;

import com.Project.BookStore.Filters.JWTFilter;
import com.Project.BookStore.Service.MyUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private MyUserService service;
    private JWTFilter jwtFilter;

    public SecurityConfig(MyUserService service, JWTFilter jwtFilter){
        this.service = service;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf(customizer -> customizer.disable())
               .authorizeHttpRequests(request -> request.requestMatchers("/user/register", "/user/login", "admin/register", "admin/login").permitAll() // THIS LINE MEANS LET THE USER CREATE ACCOUNT WITHOUT ENTERING BASIC AUTH
                       .requestMatchers(HttpMethod.GET,"/books/**").authenticated() //THIS LINE LET THE USER SEE ALL THE BOOKS
                       .requestMatchers(HttpMethod.POST,"/books/**").hasAuthority("ADMIN") // THIS LINE LET THE ADMIN TO DO POST OPERATION ON BOOKS
                       .requestMatchers(HttpMethod.PUT,"/books/**").hasAuthority("ADMIN")
                       .requestMatchers(HttpMethod.DELETE,"/books/**").hasAuthority("ADMIN")
                       .anyRequest().authenticated())
               .httpBasic(Customizer.withDefaults())
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // IT MEANS ADDING jwtFilter FILTER BEFORE USERNAME AND PASSWORD VERIFICATION
               .build();
    }

    @Bean
    AuthenticationProvider authProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(service);
        return provider;
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

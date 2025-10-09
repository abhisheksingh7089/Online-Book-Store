package com.Project.BookStore.Security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Online Book Store API Documentation")
                        .version("1.0")
                        .description("Java Spring Boot backend for managing books, users, and orders with secure role-based access.")
                        .contact(new Contact().name("Abhishek Kumar Singh")
                                .email("abhiishekkrsingh@gmail.com")));
    }
}

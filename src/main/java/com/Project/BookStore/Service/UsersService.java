package com.Project.BookStore.Service;

import com.Project.BookStore.Model.Users;
import com.Project.BookStore.Repository.BookRepo;
import com.Project.BookStore.Repository.UsersRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersRepo repo;
    private AuthenticationManager authManager;
    private JWTService jwtService;
    private BookRepo bookRepo;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public UsersService(UsersRepo repo, AuthenticationManager authManager, JWTService jwtService, BookRepo bookRepo){
        this.authManager = authManager;
        this.repo = repo;
        this.jwtService = jwtService;
        this.bookRepo = bookRepo;
    }

    public ResponseEntity<Boolean>registerUser(Users user){
        try{
            if(!repo.existsByUsername(user.getUsername())){
                user.setRole("USER");
                user.setPassword(encoder.encode(user.getPassword()));
                repo.save(user);
                return new ResponseEntity<>(true,HttpStatus.CREATED);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public ResponseEntity<String> verifyUser(Users user) {
        try{
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if(authentication.isAuthenticated()){
                return new ResponseEntity<>(jwtService.generateJwtToken(user.getUsername()), HttpStatus.OK);
            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed"+e.getMessage());
        }
    }

}

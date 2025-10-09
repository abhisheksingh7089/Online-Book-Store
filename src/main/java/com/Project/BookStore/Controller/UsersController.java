package com.Project.BookStore.Controller;

import com.Project.BookStore.Model.Users;
import com.Project.BookStore.Service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name="User", description = "User can register itself or login")
public class UsersController {

    private UsersService service;

    public UsersController(UsersService service){
        this.service = service;
    }

    @PostMapping("/register")
    @Operation(summary = "Signup", description = "User can create account")
    public ResponseEntity<Boolean> registerUser(@RequestBody Users user){
       return service.registerUser(user);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "User can login account")
    public ResponseEntity<String> loginUser(@RequestBody Users user){
        return service.verifyUser(user);
    }

}

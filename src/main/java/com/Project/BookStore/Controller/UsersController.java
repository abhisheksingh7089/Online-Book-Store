package com.Project.BookStore.Controller;

import com.Project.BookStore.Model.Users;
import com.Project.BookStore.Service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {

    private UsersService service;

    public UsersController(UsersService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody Users user){
       return service.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users user){
        return service.verifyUser(user);
    }

}

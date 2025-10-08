package com.Project.BookStore.Controller;

import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Model.Users;
import com.Project.BookStore.Service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    private AdminService service;

    public AdminController(AdminService service){
        this.service = service;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> registerAdmin(@RequestBody Users user){
        return service.registerAdmin(user);
    }

    @PostMapping("/login")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> loginUser(@RequestBody Users user){
        return service.verifyAdmin(user);
    }

    @PostMapping("/addBooks")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Books> addBook(@RequestBody Books book){
        return service.addBooks(book);
    }

    @PutMapping("/updateBooks/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Books> updateBook(@RequestBody Books book, @PathVariable int id){
        return service.updateBooks(book, id);
    }

    @DeleteMapping("/DeleteBooks/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return service.deleteBook(id);
    }

}

package com.Project.BookStore.Controller;

import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Model.Users;
import com.Project.BookStore.Service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@Tag(name="Admin", description = "ADMIN CRUD operations")
public class AdminController {

    private AdminService service;

    public AdminController(AdminService service){
        this.service = service;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Signup", description = "Admin can create account")
    public ResponseEntity<Boolean> registerAdmin(@RequestBody Users user){
        return service.registerAdmin(user);
    }

    @PostMapping("/login")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Login", description = "Admin can login account")
    public ResponseEntity<String> loginUser(@RequestBody Users user){
        return service.verifyAdmin(user);
    }

    @PostMapping("/addBooks")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add Book", description = "Admin can add book")
    public ResponseEntity<String> addBook(@RequestBody Books book){
        return service.addBooks(book);
    }

    @PutMapping("/updateBooks/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update Book", description = "Admin can update book")
    public ResponseEntity<Books> updateBook(@RequestBody Books book, @PathVariable int id){
        return service.updateBooks(book, id);
    }

    @DeleteMapping("/DeleteBooks/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Book", description = "Admin can delete book")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return service.deleteBook(id);
    }

}

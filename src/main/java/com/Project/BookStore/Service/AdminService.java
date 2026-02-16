package com.Project.BookStore.Service;

import com.Project.BookStore.Model.Books;
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

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AdminService {

    private UsersRepo repo;
    private AuthenticationManager authManager;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private JWTService jwtService;
    private BookRepo bookRepo;
    public AdminService(UsersRepo repo, AuthenticationManager authManager, JWTService jwtService, BookRepo bookRepo){
        this.repo = repo;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.bookRepo = bookRepo;
    }
    public ResponseEntity<Boolean> registerAdmin(Users user) {
        try{
            if(!repo.existsByUsername(user.getUsername())){
                user.setRole("ADMIN");
                user.setPassword(encoder.encode(user.getPassword()));
                repo.save(user);
            }
            return new ResponseEntity<>(true,HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<String> verifyAdmin(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return new ResponseEntity<>(jwtService.generateJwtToken(user.getUsername()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Fail", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<String> addBooks(Books book) {
        try{
            if(bookRepo.existsByTitleAndAuthor(book.getTitle(), book.getAuthor())){
                return new ResponseEntity<>("Book with same title and author already exist", HttpStatus.CONFLICT);
            }
            else {
                bookRepo.save(book);
                return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Books> updateBooks(Books book, int id) {
        try{
            Optional<Books> existingBook = bookRepo.findById(id);
            if(existingBook.isPresent()){
                Books updateBook = existingBook.get();
                updateBook.setTitle(book.getTitle());
                updateBook.setAuthor(book.getAuthor());
                updateBook.setPrice(book.getPrice());
                return new ResponseEntity<>(bookRepo.save(updateBook), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteBook(int id) {
        try{
            if(bookRepo.existsById(id)){
                bookRepo.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Record Deleted Successfully");
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail" + e.getMessage());
        }
    }
}

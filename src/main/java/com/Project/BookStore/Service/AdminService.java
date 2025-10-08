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
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<String> verifyAdmin(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return new ResponseEntity<>(jwtService.generateJwtToken(user.getUsername()), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Books> addBooks(Books book) {
        return new ResponseEntity<>(bookRepo.save(book), HttpStatus.OK);
    }

    public ResponseEntity<Books> updateBooks(Books book, int id) {
        try{
            if(bookRepo.existsById(id))
                return new ResponseEntity<>(bookRepo.save(book), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public ResponseEntity<String> deleteBook(int id) {
        try{
            if(bookRepo.existsById(id)){
                bookRepo.deleteById(id);
                return new ResponseEntity<>("Record Deleted Sucessfully", HttpStatus.OK);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}

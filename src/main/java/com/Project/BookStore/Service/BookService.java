package com.Project.BookStore.Service;

import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Repository.BookRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepo repo;

    public BookService(BookRepo repo){
        this.repo = repo;
    }

    public ResponseEntity<List<Books>> getAllBooks() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Books> getBookByTitle(String title) {
        try{
            return new ResponseEntity<>(repo.findByTitle(title),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

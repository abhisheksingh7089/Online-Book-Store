package com.Project.BookStore.Controller;

import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
public class BooksController {

    private BookService service;

    public BooksController(BookService service){
        this.service = service;
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Books>> getAllBooks(){
        return service.getAllBooks();
    }

    @GetMapping("/bookByTtitle/{title}")
    public ResponseEntity<Books> getBookByTitle(@RequestParam String title){
        return service.getBookByTitle(title);
    }
}

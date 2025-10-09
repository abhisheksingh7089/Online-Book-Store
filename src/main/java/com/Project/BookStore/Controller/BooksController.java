package com.Project.BookStore.Controller;

import com.Project.BookStore.DTO.BooksDTO;
import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
@Tag(name="Books", description = "User related services")
public class BooksController {

    private BookService service;

    public BooksController(BookService service){
        this.service = service;
    }

    @GetMapping("/getAllBooks")
    @Operation(summary = "Get all the books", description = "User can get all the books from the database")
    public ResponseEntity<List<Books>> getAllBooks(){
        return service.getAllBooks();
    }

    @GetMapping("/bookByTtitle/{title}")
    @Operation(summary = "Get book by title", description = "User can get the books by entering book's title")
    public ResponseEntity<List<BooksDTO>> getBookByTitle(@PathVariable String title){
        return service.getBookByTitle(title);
    }
}

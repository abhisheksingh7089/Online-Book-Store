package com.Project.BookStore.Service;

import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Repository.BookRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.print.Book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    BookRepo bookRepo;
    @InjectMocks
    AdminService service;

    @Test
    public void test_AddBook_Success(){
        Books book = new Books();
        book.setTitle("Atomic Habits");
        book.setAuthor("James Clear");
        when(bookRepo.existsByTitleAndAuthor(anyString(), anyString())).thenReturn(false);
        ResponseEntity<String> response = service.addBooks(book);
        assertEquals(response.getBody(),"Book added successfully");
        verify(bookRepo, times(1)).save(book);
    }

    @Test
    public void test_DeleteBook_NotFound(){
        int bookId = 1009;
        when(bookRepo.existsById(bookId)).thenReturn(false);
        ResponseEntity<String> response = service.deleteBook(bookId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookRepo, times(0)).deleteById(bookId);
    }

}
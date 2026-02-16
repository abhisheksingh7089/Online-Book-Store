package com.Project.BookStore.Service;

import com.Project.BookStore.DTO.BooksDTO;
import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Repository.BookRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepo repo;

    @InjectMocks
    private BookService service;

    @Test
    void testGetBookByTitleException(){
        String title = "Lost Horizons";

        when(repo.findByTitle(title)).thenThrow(new RuntimeException("DB connection fail"));
        ResponseEntity<List<BooksDTO>> listOfBooks =  service.getBookByTitle(title);
        assertEquals(HttpStatus.BAD_REQUEST, listOfBooks.getStatusCode());
        assertNull(listOfBooks.getBody());
    }


}
package com.Project.BookStore.Service;

import com.Project.BookStore.DTO.OrderedBook;
import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Model.Order;
import com.Project.BookStore.Model.Users;
import com.Project.BookStore.Repository.BookRepo;
import com.Project.BookStore.Repository.OrderRepo;
import com.Project.BookStore.Repository.UsersRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;
    @Mock
    private BookRepo bookRepo;
    @Mock
    private UsersRepo usersRepo;
    @InjectMocks
    private OrderService service;

    @Test
    public void test_InsufficientStock(){
        Books mockBook = new Books();
        int userId = 101, bookId = 1, quantityRequested = 10;
        mockBook.setStock(5);
        when(bookRepo.findById(bookId)).thenReturn(Optional.of(mockBook));
        when(usersRepo.findById(userId)).thenReturn(Optional.of(new Users()));
        ResponseEntity<String> response = service.placeOrder(userId,bookId,quantityRequested);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Not enough stock available"));
        verify(orderRepo, times(0)).save(any(Order.class));
    }

    @Test
    public void test_BookNotFound(){
        int userId = 101;
        when(orderRepo.findOrderdBooks(userId)).thenReturn(new ArrayList<>());
        ResponseEntity<List<OrderedBook>> response = service.getMyOrder(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    public void test_DBCrashException(){
        int userId = 101;
        when(orderRepo.findOrderdBooks(userId)).thenThrow(new RuntimeException("DB error"));
        ResponseEntity<List<OrderedBook>> response =  service.getMyOrder(userId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
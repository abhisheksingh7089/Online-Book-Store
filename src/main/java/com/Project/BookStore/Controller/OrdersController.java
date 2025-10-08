package com.Project.BookStore.Controller;

import com.Project.BookStore.DTO.OrderedBook;
import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrdersController {

    private OrderService service;
    public OrdersController(OrderService service){
        this.service = service;

    }

    @PostMapping("placeOrder/{userId}/{bookId}/{quantity}")
    public ResponseEntity<String> placeOrder(@PathVariable int userId, @PathVariable int bookId, @PathVariable int quantity){
        return service.placeOrder(userId, bookId, quantity);
    }

    @GetMapping("/myOrder/{userId}")
    public ResponseEntity<List<OrderedBook>> getMyOrder(@PathVariable int userId){
        return service.getMyOrder(userId);
    }

}

package com.Project.BookStore.Controller;

import com.Project.BookStore.DTO.OrderedBook;
import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@Tag(name="Order service", description = "User order services")
public class OrdersController {

    private OrderService service;
    public OrdersController(OrderService service){
        this.service = service;

    }

    @PostMapping("placeOrder/{userId}/{bookId}/{quantity}")
    @Operation(summary = "Place Order", description = "User can place order by entering book id, user id and quantity")
    public ResponseEntity<String> placeOrder(@PathVariable int userId, @PathVariable int bookId, @PathVariable int quantity){
        return service.placeOrder(userId, bookId, quantity);
    }

    @GetMapping("/myOrder/{userId}")
    @Operation(summary = "Get My Order", description = "User can fetch its orders")
    public ResponseEntity<List<OrderedBook>> getMyOrder(@PathVariable int userId){
        return service.getMyOrder(userId);
    }

}

package com.Project.BookStore.Service;

import com.Project.BookStore.DTO.OrderedBook;
import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Model.Order;
import com.Project.BookStore.Model.Users;
import com.Project.BookStore.Repository.BookRepo;
import com.Project.BookStore.Repository.OrderRepo;
import com.Project.BookStore.Repository.UsersRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepo repo;
    private BookRepo bookRepo;
    private UsersRepo usersRepo;

    public OrderService(OrderRepo repo, BookRepo bookRepo, UsersRepo usersRepo){
        this.bookRepo = bookRepo;
        this.usersRepo = usersRepo;
        this.repo = repo;
    }

    public ResponseEntity<String> placeOrder(int userId, int bookId, int quantity) {

        Order order = new Order();
       try{
           Books book = bookRepo.findById(bookId).get();
           Users user = usersRepo.findById(userId).get();
           if(book.getStock() >= quantity){
                order.setBook(book);
                order.setUser(user);
                order.setQuantity(quantity);
                book.setStock(book.getStock() -quantity); //This line reduce the stock size by 1 as per order
                bookRepo.save(book); //This line update the book table because we reduce the stock by - quantity
                repo.save(order);
                return ResponseEntity.status(HttpStatus.OK).body("Order placed successfully");
           }
           else {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough stock available");
           }
       } catch (Exception e) {
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong: "+ e.getMessage());
       }
    }

    public ResponseEntity<List<OrderedBook>> getMyOrder(int userId) {
        return new ResponseEntity<>(repo.findOrderdBooks(userId), HttpStatus.OK);

    }
}

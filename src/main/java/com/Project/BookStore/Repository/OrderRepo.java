package com.Project.BookStore.Repository;

import com.Project.BookStore.DTO.OrderedBook;
import com.Project.BookStore.Model.Books;
import com.Project.BookStore.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query(""" 
            SELECT new com.Project.BookStore.DTO.OrderedBook
            ( o.id, o.orderDate, b.title, b.author, b.price, o.quantity )
            FROM Orders o
            JOIN o.book b
            WHERE o.user.id = :userId
            """)
    public List<OrderedBook> findOrderdBooks(@Param("userId") int userId);
}

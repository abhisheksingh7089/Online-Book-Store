package com.Project.BookStore.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderedBook {

    private int orderId;
    private LocalDateTime orderDate;
    private String title;
    private String author;
    private float price;
    private int quantity;

}

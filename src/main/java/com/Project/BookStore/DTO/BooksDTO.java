package com.Project.BookStore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BooksDTO {
    private String title;
    private String author;
    private float price;
}

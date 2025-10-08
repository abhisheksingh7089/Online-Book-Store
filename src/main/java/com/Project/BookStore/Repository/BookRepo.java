package com.Project.BookStore.Repository;

import com.Project.BookStore.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Books, Integer> {

    @Query("Select b.id, b.title, b.author, b.price from Books b where title = :title")
    public Books findByTitle(String title);

}

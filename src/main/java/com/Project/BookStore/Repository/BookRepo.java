package com.Project.BookStore.Repository;

import com.Project.BookStore.DTO.BooksDTO;
import com.Project.BookStore.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Books, Integer> {

    @Query("""
                Select new com.Project.BookStore.DTO.BooksDTO
                (b.title, b.author, b.price)
                from Books b 
                where b.title = :title
           """)
    public List<BooksDTO> findByTitle(String title);
    public boolean existsByTitleAndAuthor(String title, String author);

}

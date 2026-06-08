package com.matiol.library.repository;

import com.matiol.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
    List<Book> findByAvailable(boolean available);
    List<Book> findByTitleContainingIgnoreCase(String title);
}
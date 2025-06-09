package com.literatura.literalura.repository;

import com.literatura.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitleAndAuthor(String title, String author);
}
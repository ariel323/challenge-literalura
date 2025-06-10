package com.literatura.literalura.service;

import com.literatura.literalura.model.Book;
import com.literatura.literalura.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public boolean existsByTitleAndAuthor(String title, String author) {
        return bookRepository.existsByTitleAndAuthor(title, author);
    }

    public Map<String, Object> getBooksByAuthor(String author) {
        // Implementa la lógica para buscar libros por autor aquí
        // Por ejemplo, si quieres devolver una lista de libros:
        List<Book> books = bookRepository.findAll().stream()
            .filter(b -> b.getAuthor() != null && b.getAuthor().equalsIgnoreCase(author))
            .toList();
        return Map.of(
            "count", books.size(),
            "results", books
        );
    }
}

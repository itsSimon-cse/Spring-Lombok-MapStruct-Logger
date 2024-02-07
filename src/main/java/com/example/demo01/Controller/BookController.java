package com.example.demo01.Controller;

import com.example.demo01.Demo.BookDTO;
import com.example.demo01.Service.BookService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookController {

    private BookService bookService;
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/createBook")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        try {
            LOG.info("Creating a new book: {}", bookDTO);
            BookDTO createdBook = bookService.createBook(bookDTO);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error("Error occurred while creating a new book: {}", e.getMessage());
            return new ResponseEntity<>("Failed to create a new book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        LOG.info("Fetching all books");
        List<BookDTO> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        LOG.info("Fetching book with ID: {}", id);
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        LOG.info("Updating book with ID {}: {}", id, bookDTO);
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/deleteAllBooks")
    public String deleteAllBooks() {
        LOG.info("Deleting all books");
        return bookService.deleteAllBooks();
    }

    @DeleteMapping("/deleteBookById/{id}")
    public String deleteBookById(@PathVariable Long id) {
        LOG.info("Deleting book with ID: {}", id);
        return bookService.deleteBookById(id);
    }
}

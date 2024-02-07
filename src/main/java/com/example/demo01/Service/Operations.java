package com.example.demo01.Service;

import com.example.demo01.Demo.BookDTO;
import java.util.List;

public interface Operations {
    BookDTO createBook(BookDTO bookDTO);

    String deleteBookById(Long id);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    String deleteAllBooks();

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);
}

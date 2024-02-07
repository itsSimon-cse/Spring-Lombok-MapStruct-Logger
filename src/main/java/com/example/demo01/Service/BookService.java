package com.example.demo01.Service;

import com.example.demo01.Demo.BookEntity;
import com.example.demo01.Demo.BookDTO;
import com.example.demo01.Demo.mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo01.DemoRepository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements Operations {

    private final BookRepository repo;
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        try {
            BookEntity bookEntity = mapper.maptoBookEntity(bookDTO);
            BookEntity savedBookEntity = repo.save(bookEntity);
            return mapper.maptoBookDTO(savedBookEntity);
        } catch (Exception e) {
            LOG.error("Error occurred while creating a new book: {}", e.getMessage());
            throw new RuntimeException("Failed to create a new book");
        }
    }


    public List<BookDTO> getAllBooks() {
        LOG.info("Task execution started.");
        List<BookEntity> bookEntities = repo.findAll();
        return bookEntities.stream()
                .map(mapper::maptoBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        BookEntity bookEntity = repo.findById(id).orElse(null);
        return bookEntity != null ? mapper.maptoBookDTO(bookEntity) : null;
    }

    @Override
    public String deleteBookById(Long id) {
        repo.deleteById(id);
        LOG.info("Deleted book with ID: {}", id);
        return null;
    }

    @Override
    public String deleteAllBooks() {
        repo.deleteAll();
        LOG.info("All books have been deleted");
        return null;
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        BookEntity existingBookEntity = repo.findById(id).orElse(null);
        if (existingBookEntity == null) {
            LOG.info("Book with ID {} not found", id);
            return null;
        }

        // Update the fields of existingBookEntity with the values from bookDTO
        existingBookEntity.setAuthor(bookDTO.getAuthor());
        existingBookEntity.setPublisher(bookDTO.getPublisher());

        // Save the updated entity
        BookEntity updatedBookEntity = repo.save(existingBookEntity);

        // Map the updated entity to DTO and return
        return mapper.maptoBookDTO(updatedBookEntity);
    }

}

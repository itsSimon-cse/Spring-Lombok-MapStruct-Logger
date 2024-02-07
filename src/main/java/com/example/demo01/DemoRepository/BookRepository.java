package com.example.demo01.DemoRepository;

import com.example.demo01.Demo.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
}

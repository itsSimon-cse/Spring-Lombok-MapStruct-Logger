package com.example.demo01.Demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDTO {

        private String author;
        private String publisher;

        // Add other fields, getters, setters
}

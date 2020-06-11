package org.wcci.campuslibraries;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookStorage {
    Map<String, Book> books = new HashMap<>();

    public BookStorage(){
        books.put("Head First Java", new Book("Head First Java", "Kathy Sierra", "4440333044-2","Good book to learn Java."));
        books.put("Test Driven Development by Example", new Book("Test Driven Development by Example", "Kent Beck", "44443333044-2","Good book to learn TDD."));
    }

    public Book findBookByTitle(String bookTitle) {
        return books.get(bookTitle);
    }
}

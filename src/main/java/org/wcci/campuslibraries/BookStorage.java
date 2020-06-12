package org.wcci.campuslibraries;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookStorage {
    Map<String, Book> books = new HashMap<>();

    public BookStorage(){
        Book book1 = new Book("Head First Java", "Kathy Sierra", "4440333044-2", "Good book to learn Java.");
        Book book2 = new Book("Test Driven Development by Example", "Kent Beck", "44443333044-2", "Good book to learn TDD.");
        Book book3 =new Book("Head First C Sharp", "Bert Bates", "422-333044-2","Good book to learn C#.");
        Book book4 =new Book("Agile Development Principles, Patterns, and Practices for C Sharp", "Micah Martin", "4543-54-2","SOLID principles and more for C#.");
        books.put(book1.getTitle(), book1);
        books.put(book2.getTitle(), book2);
        books.put(book3.getTitle(),book3);
        books.put(book4.getTitle(),book4);
    }

    public Book findBookByTitle(String bookTitle) {
        return books.get(bookTitle);
    }

    public Collection<Book> findAllBooks() {
        return books.values();
    }
}

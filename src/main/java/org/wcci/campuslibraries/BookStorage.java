package org.wcci.campuslibraries;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookStorage {


    BookRepository bookRepo;

    public BookStorage(BookRepository bookRepo) {
        this.bookRepo = bookRepo;

        Book book1 = new Book("Head First Java", "Kathy Sierra", "4440333044-2", "Good book to learn Java.");
        Book book2 = new Book("Test Driven Development by Example", "Kent Beck", "44443333044-2", "Good book to learn TDD.");
        Book book3 = new Book("Head First C Sharp", "Bert Bates", "422-333044-2", "Good book to learn C#.");
        Book book4 = new Book("Agile Development Principles, Patterns, and Practices for C Sharp", "Micah Martin", "4543-54-2", "SOLID principles and more for C#.");

        bookRepo.save(book1);
        bookRepo.save(book2);
        bookRepo.save(book3);
        bookRepo.save(book4);
    }

    public Book findBookByTitle(String bookTitle) {
        return bookRepo.findByTitle(bookTitle);
    }

    public Iterable<Book> findAllBooks() {
        return bookRepo.findAll();
    }
}

package org.wcci.campuslibraries.storage;

import org.springframework.stereotype.Service;
import org.wcci.campuslibraries.entities.Book;
import org.wcci.campuslibraries.storage.repositories.BookRepository;

@Service
public class BookStorage {


    BookRepository bookRepo;

    public BookStorage(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book findBookByTitle(String bookTitle) {
        return bookRepo.findByTitle(bookTitle);
    }

    public Iterable<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    public void save(Book book) {
        bookRepo.save(book);
    }

    public void deleteBookById(long bookId) {
        bookRepo.deleteById(bookId);
    }
}

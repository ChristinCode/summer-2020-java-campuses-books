package org.wcci.campuslibraries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.campuslibraries.entities.Author;
import org.wcci.campuslibraries.entities.Book;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.storage.repositories.AuthorRepository;
import org.wcci.campuslibraries.storage.repositories.BookRepository;
import org.wcci.campuslibraries.storage.repositories.CampusRepository;

@Component
public class Populator implements CommandLineRunner {
    @Autowired
    CampusRepository campusRepo;
    @Autowired
    BookRepository bookRepo;
    @Autowired
    AuthorRepository authorRepo;


    @Override
    public void run(String... args) throws Exception {
        Campus columbus = new Campus("Columbus", "In picturesque South Clintonville");
        Campus cleveland = new Campus("Cleveland", "Beside to Lake Erie");
        campusRepo.save(columbus);
        campusRepo.save(cleveland);

        Author author1 = new Author("Kathy", "Sierra");
        Author author2 = new Author("Kent", "Beck");
        Author author3 = new Author("Bert", "Bates");
        Author author4 = new Author("Micah", "Martin");
        authorRepo.save(author1);
        authorRepo.save(author2);
        authorRepo.save(author3);
        authorRepo.save(author4);

        Book book1 = new Book("Head First Java", "4440333044-2", "Good book to learn Java.", columbus, author1, author3);
        Book book2 = new Book("Test Driven Development by Example", "44443333044-2", "Good book to learn TDD.", columbus, author2);
        bookRepo.save(book1);
        bookRepo.save(book2);
        Book book3 = new Book("Head First C Sharp", "422-333044-2", "Good book to learn C#.", cleveland, author3);
        Book book4 = new Book("Agile Development Principles, Patterns, and Practices for C Sharp",
                "4543-54-2", "SOLID principles and more for C#.", cleveland, author4);
        bookRepo.save(book3);
        bookRepo.save(book4);
    }
}

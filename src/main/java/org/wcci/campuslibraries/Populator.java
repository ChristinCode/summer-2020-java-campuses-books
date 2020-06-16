package org.wcci.campuslibraries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Populator implements CommandLineRunner {
    @Autowired
    CampusRepository campusRepo;
    @Autowired
    BookRepository bookRepo;


    @Override
    public void run(String... args) throws Exception {
        Campus columbus = new Campus("Columbus", "In picturesque South Clintonville");
        Campus cleveland = new Campus("Cleveland", "Beside to Lake Erie");
        campusRepo.save(columbus);
        campusRepo.save(cleveland);

        Book book1 = new Book("Head First Java", "Kathy Sierra", "4440333044-2",
                "Good book to learn Java.", columbus);
        Book book2 = new Book("Test Driven Development by Example", "Kent Beck",
                "44443333044-2", "Good book to learn TDD.", columbus);
        bookRepo.save(book1);
        bookRepo.save(book2);
        Book book3 = new Book("Head First C Sharp", "Bert Bates", "422-333044-2",
                "Good book to learn C#.", cleveland);
        Book book4 = new Book("Agile Development Principles, Patterns, and Practices for C Sharp",
                "Micah Martin", "4543-54-2",
                "SOLID principles and more for C#.",cleveland);
        bookRepo.save(book3);
        bookRepo.save(book4);
    }
}

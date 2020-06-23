package org.wcci.campuslibraries.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.campuslibraries.entities.Author;
import org.wcci.campuslibraries.entities.Book;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.storage.repositories.AuthorRepository;
import org.wcci.campuslibraries.storage.repositories.BookRepository;
import org.wcci.campuslibraries.storage.repositories.CampusRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private CampusRepository campusRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void campusesCanHaveMultipleBooks() {
        Campus testCampus = new Campus("Test Campus", "Campus used for testing.");
        campusRepo.save(testCampus);

        Book testBook1 = new Book("Book", "ISBN", "Book for testing.", testCampus);
        bookRepo.save(testBook1);
        Book testBook2 = new Book("Another Book", "ISBN-2", "Another book for testing.", testCampus);
        bookRepo.save(testBook2);

        entityManager.flush();
        entityManager.clear();

        Optional<Campus> retrievedCampusOptional = campusRepo.findById(testCampus.getId());
        Campus retrievedCampus = retrievedCampusOptional.get();

        assertThat(retrievedCampus).isEqualTo(testCampus);
        assertThat(retrievedCampus.getBooks()).containsExactlyInAnyOrder(testBook1, testBook2);

    }

    @Test
    public void authorsShouldHaveMultipleBooks() {
        Author testAuthor1 = new Author("Testy", "McTesterson");
        authorRepo.save(testAuthor1);
        Author testAuthor2 = new Author("Tester", "Testington");
        authorRepo.save(testAuthor2);
        Campus testCampus = new Campus("Test Campus", "Campus used for testing.");
        campusRepo.save(testCampus);
        Book testBook1 = new Book("Book", "ISBN", "Book for testing.", testCampus, testAuthor1);
        bookRepo.save(testBook1);
        Book testBook2 = new Book("Another Book", "ISBN-2", "Another book for testing.", testCampus, testAuthor2);
        bookRepo.save(testBook2);
        Book testBook3 = new Book("Yet Another Book", "ISBN-3", "Yet another book for testing.",
                testCampus, testAuthor1, testAuthor2);
        bookRepo.save(testBook3);

        entityManager.flush();
        entityManager.clear();

        Author retrievedAuthor1 = authorRepo.findById(testAuthor1.getId()).get();
        assertThat(retrievedAuthor1).isEqualTo(testAuthor1);
        assertThat(retrievedAuthor1.getBooks()).containsExactlyInAnyOrder(testBook1, testBook3);
        Author retrievedAuthor2 = authorRepo.findById(testAuthor2.getId()).get();
        assertThat(retrievedAuthor2.getBooks()).containsExactlyInAnyOrder(testBook2, testBook3);
    }

}

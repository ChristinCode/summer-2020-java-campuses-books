package org.wcci.campuslibraries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.logging.Handler;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private CampusRepository campusRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void campusesCanHaveMultipleBooks(){
        Campus testCampus = new Campus("Test Campus", "Campus used for testing.");
        campusRepo.save(testCampus);

        Book testBook1 = new Book("Book", "Tester", "ISBN", "Book for testing.", testCampus);
        bookRepo.save(testBook1);
        Book testBook2 = new Book("Another Book", "Testy", "ISBN-2", "Another book for testing.", testCampus);
        bookRepo.save(testBook2);

        entityManager.flush();
        entityManager.clear();

        Optional<Campus> retrievedCampusOptional = campusRepo.findById(testCampus.getId());
        Campus retrievedCampus = retrievedCampusOptional.get();

        assertThat(retrievedCampus).isEqualTo(testCampus);
        assertThat(retrievedCampus.getBooks()).containsExactlyInAnyOrder(testBook1, testBook2);

    }

}

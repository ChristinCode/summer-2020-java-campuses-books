package org.wcci.campuslibraries.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.campuslibraries.entities.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByTitle(String bookTitle);
}

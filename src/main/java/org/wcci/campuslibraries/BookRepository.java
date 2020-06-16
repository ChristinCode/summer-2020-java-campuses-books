package org.wcci.campuslibraries;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByTitle(String bookTitle);
}

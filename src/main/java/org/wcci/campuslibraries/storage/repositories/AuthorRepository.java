package org.wcci.campuslibraries.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.campuslibraries.entities.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}

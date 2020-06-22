package org.wcci.campuslibraries;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorStorage {
    private AuthorRepository authorRepo;
    public AuthorStorage (AuthorRepository authorRepo){
        this.authorRepo = authorRepo;
    }
    public Author findAuthorById(Long id) {
        return authorRepo.findById(id).get();
    }

    public void save(Author authorToSave) {
        authorRepo.save(authorToSave);
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepo.findAll();
    }
}

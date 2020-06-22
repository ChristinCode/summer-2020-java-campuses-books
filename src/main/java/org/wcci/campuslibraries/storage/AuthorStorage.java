package org.wcci.campuslibraries.storage;

import org.springframework.stereotype.Service;
import org.wcci.campuslibraries.entities.Author;
import org.wcci.campuslibraries.exceptions.ResourceNotFoundException;
import org.wcci.campuslibraries.storage.repositories.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorStorage {
    private AuthorRepository authorRepo;
    public AuthorStorage (AuthorRepository authorRepo){
        this.authorRepo = authorRepo;
    }
    public Author findAuthorById(Long id) {
        Optional<Author> authorOptional = authorRepo.findById(id);
        Author retrievedAuthor;
        if(authorOptional.isEmpty()){
            throw new ResourceNotFoundException("Author not found.");
        }else{
           retrievedAuthor= authorOptional.get();
        }
        return retrievedAuthor;
    }

    public void save(Author authorToSave) {
        authorRepo.save(authorToSave);
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepo.findAll();
    }
}

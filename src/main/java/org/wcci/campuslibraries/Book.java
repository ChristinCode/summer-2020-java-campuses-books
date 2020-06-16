package org.wcci.campuslibraries;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String author;
    private String isbn;
    private String description;

    protected Book(){}

    public Book(String title, String author, String isbn, String description) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }
}

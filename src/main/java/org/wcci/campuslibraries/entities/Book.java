package org.wcci.campuslibraries.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @ManyToMany
    private Collection<Author> authors;
    private String isbn;
    private String description;
    @ManyToOne
    private Campus campus;

    protected Book(){}

    public Book(String title, String isbn, String description, Campus campus, Author... authors) {
        this.title = title;
        this.authors = new ArrayList<>(Arrays.asList(authors));
        this.isbn = isbn;
        this.description = description;
        this.campus = campus;
    }

    public String getTitle() {
        return title;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public Campus getCampus() {
        return campus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", campus=" + campus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(title, book.title) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(description, book.description) &&
                Objects.equals(campus, book.campus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, description, campus);
    }
}

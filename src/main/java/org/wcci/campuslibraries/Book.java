package org.wcci.campuslibraries;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String author;
    private String isbn;
    private String description;
    @ManyToOne
    private Campus campus;

    protected Book(){}

    public Book(String title, String author, String isbn, String description, Campus campus) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
        this.campus = campus;
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
                ", author='" + author + '\'' +
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
                Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(description, book.description) &&
                Objects.equals(campus, book.campus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, description, campus);
    }
}

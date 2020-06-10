package org.wcci.campuslibraries;

import java.util.Collection;

public class Campus {
    private String name;
    private String description;
    private Collection<Book> books;

    public Campus(String name, String description, Collection<Book> books) {
        this.name = name;
        this.description = description;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Collection<Book> getBooks() {
        return books;
    }
}

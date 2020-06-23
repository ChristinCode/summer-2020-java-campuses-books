package org.wcci.campuslibraries.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.campuslibraries.entities.Author;
import org.wcci.campuslibraries.entities.Book;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.storage.AuthorStorage;
import org.wcci.campuslibraries.storage.BookStorage;
import org.wcci.campuslibraries.storage.CampusStorage;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private BookStorage bookStorage;
    private CampusStorage campusStorage;
    private AuthorStorage authorStorage;


    public BookController(BookStorage bookStorage, CampusStorage campusStorage, AuthorStorage authorStorage) {
        this.bookStorage = bookStorage;
        this.campusStorage = campusStorage;
        this.authorStorage = authorStorage;
    }

    @GetMapping("books/{bookTitle}")
    public String showSingleBook(@PathVariable String bookTitle, Model model) {
        model.addAttribute("bookToDisplay", bookStorage.findBookByTitle(bookTitle));
        return "book-template";
    }
    @PostMapping("books/add")
    public String addBook(String title, String isbn, String description, String campusName, long... authorIds) {
        Campus bookCampus = campusStorage.findCampusByName(campusName);
        Collection<Author> bookAuthors = Arrays.stream(authorIds)
                                               .mapToObj(id->authorStorage.findAuthorById(id))
                                               .collect(Collectors.toSet());
        bookStorage.save(new Book(title,isbn,description,bookCampus, bookAuthors.toArray(Author[]::new)));
        return "redirect:/campuses/"+campusName;
    }
    @PostMapping("books/delete")
    public String deleteBook(long bookId){
        bookStorage.deleteBookById(bookId);
        return "redirect:/";
    }
}

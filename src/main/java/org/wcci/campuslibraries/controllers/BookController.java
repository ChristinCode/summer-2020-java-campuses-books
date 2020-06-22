package org.wcci.campuslibraries.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wcci.campuslibraries.storage.BookStorage;

@Controller
public class BookController {

    BookStorage bookStorage;


    public BookController(BookStorage bookStorage){
       this.bookStorage=bookStorage;
    }

    @RequestMapping("books/{bookTitle}")
    public String showSingleBook(@PathVariable String bookTitle, Model model) {
        model.addAttribute("bookToDisplay", bookStorage.findBookByTitle(bookTitle));
        return "book-template";
    }
}

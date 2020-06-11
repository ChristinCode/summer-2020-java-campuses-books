package org.wcci.campuslibraries;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BookController {

    BookStorage bookStorage;


    public BookController(BookStorage bookStorage){
        this.bookStorage = bookStorage;
    }

    @RequestMapping("books/{bookTitle}")
    public String showSingleBook(@PathVariable String bookTitle, Model model){
        model.addAttribute("book", bookStorage.findBookByTitle(bookTitle));
        return "book-template";
    }


}

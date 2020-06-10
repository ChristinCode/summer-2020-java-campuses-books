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

    Map<String, Book> books = new HashMap<>();

    public BookController(){
        books.put("Head First Java", new Book("Head First Java", "Kathy Sierra", "4440333044-2","Good book to learn Java."));
        books.put("Test Driven Development by Example", new Book("Test Driven Development by Example", "Kent Beck", "44443333044-2","Good book to learn TDD."));
    }

    @RequestMapping("books/{bookTitle}")
    public String showSingleBook(@PathVariable String bookTitle, Model model){
        model.addAttribute("book", books.get(bookTitle));
        return "book-template";
    }


}

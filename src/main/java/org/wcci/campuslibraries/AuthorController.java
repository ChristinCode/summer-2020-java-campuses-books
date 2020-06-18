package org.wcci.campuslibraries;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {
    private AuthorRepository authorRepo;

    public AuthorController(AuthorRepository authorRepo){
        this.authorRepo = authorRepo;
    }

    @GetMapping("authors/{id}")
    public String showSingleAuthor(@PathVariable Long id, Model model){
        model.addAttribute("author", authorRepo.findById(id).get());
        return "author-template";
    }
    @PostMapping("authors/add")
    public String addAuthor(String firstName, String lastName){
        Author authorToAdd = new Author(firstName, lastName);
        authorRepo.save(authorToAdd);
        return "redirect:/";
    }
}

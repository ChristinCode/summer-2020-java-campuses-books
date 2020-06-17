package org.wcci.campuslibraries;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final CampusRepository campusRepo;
    private final AuthorRepository authorRepo;

    public HomeController(CampusRepository campusRepo, AuthorRepository authorRepo) {
        this.campusRepo = campusRepo;
        this.authorRepo = authorRepo;
    }

    @RequestMapping({"", "/"})
    public String routeToCampuses(Model model) {
        model.addAttribute("campuses", campusRepo.findAll());
        model.addAttribute("authors", authorRepo.findAll());
        return "home-template";
    }
}

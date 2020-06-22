package org.wcci.campuslibraries.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.storage.CampusStorage;


@Controller
public class CampusController {
    private CampusStorage campusStorage;

    public CampusController(CampusStorage campusStorage) {
        this.campusStorage = campusStorage;
    }

    @GetMapping("campuses/{campusName}")
    public String showSingleCampus(@PathVariable String campusName, Model model) {
        model.addAttribute("campus", campusStorage.findCampusByName(campusName));
        return "campus-template";
    }

    @GetMapping("campuses")
    public String showAllCampuses(Model model){
        model.addAttribute("campuses", campusStorage.findAllCampuses());
        return "home-template";
    }
    @PostMapping("campuses/add")
    public String addNewCampus(String name, String description) {
        if(campusStorage.findCampusByName(name)!=null){
          return "redirect:/";
        }
        Campus campusToAdd = new Campus(name, description);
        campusStorage.addCampus(campusToAdd);
        return "redirect:/";
    }
}

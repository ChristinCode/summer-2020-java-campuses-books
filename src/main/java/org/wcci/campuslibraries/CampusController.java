package org.wcci.campuslibraries;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CampusController {
    private CampusStorage campusStorage;

    public CampusController(CampusStorage campusStorage) {
        this.campusStorage = campusStorage;
    }

    @RequestMapping("campuses/{campusName}")
    public String showSingleCampus(@PathVariable String campusName, Model model) {
        model.addAttribute("campus", campusStorage.findCampusByName(campusName));
        return "campus-template";
    }
}

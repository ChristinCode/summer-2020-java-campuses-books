package org.wcci.campuslibraries;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {

    @RequestMapping({"","/"})
    public String routeToCampuses(){
       return "redirect:campuses";
    }
}

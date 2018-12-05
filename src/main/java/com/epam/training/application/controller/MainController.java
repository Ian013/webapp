package com.epam.training.application.controller;

import com.epam.training.application.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    private final CourseService courseService;

    @Autowired
    public MainController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String errorMap()    {
        return "error" ;
    }

    @RequestMapping(value = "/")
    public String index(Model model){
        model.addAttribute("courses",courseService.getAll());
        return "index";
    }

}

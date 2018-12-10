package com.epam.training.application.controller;

import com.epam.training.application.domain.User;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private final static Logger LOG =Logger.getLogger(MainController.class);
    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public MainController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @RequestMapping(value = "/403",method = RequestMethod.GET)
    public String errorMapping()    {
        return "403";
    }

    @RequestMapping(value = "/")
    public String index(Model model){
        model.addAttribute("courses",courseService.getAll());

        return "index";
    }

}

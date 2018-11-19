package com.epam.training.application.controller;

import com.epam.training.application.dao.model.Course;
import com.epam.training.application.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private CourseServiceImpl courseService;

    @Autowired
    public MainController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/")
    public String index(Model model){
        return "welcome";
    }

    @RequestMapping(value = "/courses")
    public String getCourses(Model model){
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }
}
/*      @RequestMapping(value ="/",method = RequestMethod.GET)
    public String getCourses( Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }
    }

*/

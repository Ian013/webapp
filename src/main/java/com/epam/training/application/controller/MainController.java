package com.epam.training.application.controller;

import com.epam.training.application.domain.Course;
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

import java.sql.Date;
import java.util.List;

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
        return "error";
    }

    @RequestMapping(value = "/")
    public String index(Model model, Authentication auth){
        model.addAttribute("courses",courseService.getAll());
        model.addAttribute("currentDate",new Date(System.currentTimeMillis()));
        try {
            if (auth!=null) {
                model.addAttribute("auth",
                    auth.isAuthenticated());
                User user = userService.getUserByEmail(auth.getName());
                int id = user.getId();
                if(auth.getAuthorities()
                        .stream()
                        .anyMatch((a)-> a.toString().equals("teacher"))){

                    List<Course> coursesForTeacher = courseService.getCoursesForTeacher(id);

                    coursesForTeacher.forEach((c)->
                            c.setUsers(userService.getStudentsFromCourse(c.getId())));

                    model.addAttribute("coursesForTeacher",
                            coursesForTeacher);
                }
                if(auth.getAuthorities()
                        .stream()
                        .anyMatch((a)-> a.toString().equals("student"))){

                    model.addAttribute("coursesForStudent",
                            courseService.getCoursesForStudent(id));
                }
                if(auth.getAuthorities()
                        .stream()
                        .anyMatch((a)-> a.toString().equals("admin"))){
                    List<Course> adminCourses = courseService.getAll();
                    adminCourses.forEach((c)->
                            c.setUsers(userService.getStudentsFromCourse(c.getId())));
                    model.addAttribute("courses",adminCourses);
                    model.addAttribute("teachers",userService.getAllTeachers());
                }
            }
        }catch (NullPointerException e){
            LOG.error(e.getMessage()+"Null pointer");
            return "index";
        }
        return "index";
    }

}

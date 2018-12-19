package com.epam.training.application.controller;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;
import com.epam.training.application.service.ArchiveService;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.util.List;

@Controller
public class MainController {

    private final static Logger LOG =Logger.getLogger(MainController.class);

    private final CourseService courseService;
    private final UserService userService;
    private final ArchiveService archiveService;

    @Autowired
    public MainController(CourseService courseService, UserService userService, ArchiveService archiveService) {
        this.courseService = courseService;
        this.userService = userService;
        this.archiveService = archiveService;
    }
    @RequestMapping(value = "/*",method = RequestMethod.GET)
    public String handle() {
        LOG.error("404");
        return "redirect:/";
    }

    @RequestMapping(value = "/403",method = RequestMethod.GET)
    public String errorMapping()    {
        LOG.error("Forbidden");
        return "errorPage";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model, Authentication auth,
                        @ModelAttribute(value = "flashError")String errorMessage){
        model.addAttribute("courses",courseService.getAll());
        model.addAttribute("currentDate",new Date(System.currentTimeMillis()));
        if (errorMessage!=null){
            model.addAttribute("flashError",errorMessage);
        }
            if (auth!=null) {
                User user = userService.getUserByEmail(auth.getName());
                model.addAttribute("currentUser",user);
                int id = user.getId();
                //Loads data if user is a teacher
                if(auth.getAuthorities()
                        .stream()
                        .anyMatch((a)-> a.toString().equals("teacher"))){

                    List<Course> coursesForTeacher = courseService.getCoursesForTeacher(id);

                    coursesForTeacher.forEach((c)->
                            c.setUsers(userService.getStudentsFromCourse(c.getId())));
                    model.addAttribute("coursesForTeacher",coursesForTeacher);
                    model.addAttribute("allMarks",archiveService.getAll());
                 }
                //Loads data if user is a student
                if(auth.getAuthorities()
                        .stream()
                        .anyMatch((a)-> a.toString().equals("student"))){

                    List<Course> studentCourses = courseService.getCoursesForStudent(id);
                    model.addAttribute("coursesForStudent",
                            studentCourses);
                    model.addAttribute("marks",archiveService.getArchiveNotesForStudent(user.getId()));
                }
                //Same for admin
                if(auth.getAuthorities()
                        .stream()
                        .anyMatch((a)-> a.toString().equals("admin"))){

                    List<Course> adminCourses = courseService.getAll();

                    adminCourses.forEach((c)->
                            c.setUsers(userService.getStudentsFromCourse(c.getId())));

                    model.addAttribute("courses",adminCourses);
                    model.addAttribute("teachers",userService.getAllTeachers());
                    model.addAttribute("allMarks",archiveService.getAll());
                }
            }
        return "index";
    }

}

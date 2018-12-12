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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
 private final static Logger LOG = Logger.getLogger(StudentController.class);
    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public StudentController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getAllUsers(Model model, Authentication auth,
                              @RequestParam(value = "error", required = false) String error ){
        if (error != null) {
            model.addAttribute("error", "An error occupied.");
        }
        if(auth.isAuthenticated()) {
            User teacher = userService.getUserByEmail(auth.getName());
            List<Course> courses = courseService.getCoursesForTeacher(teacher.getId());
            try {

                List<User> studentsForCourse = userService.getStudentsFromCourse(courses.get(0).getId());
                // users.forEach((s)->s.setCourses(courseService.getCoursesForStudent(s.getId())));
                model.addAttribute("courses",
                        courseService.getCoursesForTeacher(teacher.getId()));
                if(studentsForCourse!=null) {
               model.addAttribute("students", studentsForCourse);
           }}catch (IndexOutOfBoundsException e){
                LOG.error(e.getMessage());
            }
           return "users";
        }else
            return"redirect:/";
    }
}

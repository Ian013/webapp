package com.epam.training.application.controller;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public CourseController(CourseService courseService,  UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }


    @RequestMapping(value = "/courses",method = RequestMethod.GET)
    public String getCoursesForStudent(Model model, Authentication auth){
        List<Course> courses = courseService.getAll();
        if(auth.isAuthenticated()) {
            User user = userService.getUserByEmail(auth.getName());
            if(courseService.getCoursesForStudent(user.getId())!=null){
                model.addAttribute("myCourses", courseService.getCoursesForStudent(user.getId()));
            }
        }
        model.addAttribute("courses", courses);
        model.addAttribute("teachers",userService.getAllTeachers());
        return "courses";
    }

    @RequestMapping(value="/courses", method = RequestMethod.POST)
    public String addNewCourse(@RequestParam(value = "title")@NotNull  String title,
                               @RequestParam(value = "startDate")@NotNull Date start,
                               @RequestParam(value = "endDate")@NotNull Date endDate,
                               @RequestParam(value = "teacher")@NotNull  int teacherId){

        Course course = new Course(title,start, endDate,userService.getById(teacherId));
        courseService.saveOrUpdate(course);
        return "redirect:/courses";
    }

    @RequestMapping(value="delete/{id}", method=RequestMethod.GET)
    public String deleteCourse(@PathVariable Integer id) {
        courseService.remove(id);
        return "redirect:/courses";
    }
    @RequestMapping(value="deleteMyCourse/{id}")
    public String deleteCourseForStudent(@PathVariable int id,Authentication auth){
        User user = userService.getUserByEmail(auth.getName());
        userService.removeCourseForUser(user.getId(),id);
        return "redirect:/courses";
    }
    @RequestMapping(value = "addCourse/{id}", method = RequestMethod.GET)
    public  String addCourseForStudent(@PathVariable Integer id, Authentication auth){
        User user = userService.getUserByEmail(auth.getName());

           userService.addCourse(user.getId(), id);
       return "redirect:/";
    }
    @RequestMapping(value = "showCoursesForStudent",method = RequestMethod.GET)
    public String showCoursesForStudent(Authentication auth,Model model){
        getCoursesForStudent(model, auth);
        return "index";
    }
}

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.sql.Date;
@Controller
public class CourseController {


    private final static Logger LOG = Logger.getLogger(StudentController.class);

    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public CourseController(CourseService courseService,  UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @RequestMapping(value="/addNewCourse", method = RequestMethod.POST)
    public String addNewCourse(@RequestParam(value = "title")@NotNull  String title,
                               @RequestParam(value = "startDate")@NotNull Date start,
                               @RequestParam(value = "endDate")@NotNull Date endDate,
                               @RequestParam(value = "teacher")@NotNull  int teacherId){

        Course course = new Course(title,start, endDate,userService.getById(teacherId));
        courseService.saveOrUpdate(course);
        LOG.debug("new course is added: \n ".concat(course.toString()));
        return "redirect:/";
    }

    @RequestMapping(value="deleteCourse/{id}", method=RequestMethod.GET)
    public String deleteCourse(@PathVariable Integer id) {
        courseService.remove(id);
        LOG.debug(String.format("course num %d is removed successfully", id));
        return "redirect:/";
    }
    @RequestMapping(value="deleteMyCourse/{id}")
    public String deleteCourseForStudent(@PathVariable int id,
                                         Authentication auth){
        User user = userService.getUserByEmail(auth.getName());
        userService.removeCourseForUser(user.getId(),id);
        LOG.debug(String.format("course num %d is removed for user %d (%s)",id, user.getId(),user.getEmail()));
        return "redirect:/";
    }
    @RequestMapping(value = "addCourse/{id}", method = RequestMethod.GET)
    public  String addCourseForStudent(@PathVariable Integer id,
                                       Authentication auth,
                                       Model model){
        User user = userService.getUserByEmail(auth.getName());
        if(courseService.getCoursesForStudent(user.getId())
                .stream()
                .anyMatch((c)->c.equals(courseService.getById(id)))){
            model.addAttribute("error","You already have this course!");
                return "redirect:/";
        }else{
            userService.addCourse(user.getId(), id);
            return "redirect:/";
        }
    }
}

package com.epam.training.application.controller;

import com.epam.training.application.domain.Course;
import com.epam.training.application.service.ArchiveService;
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
import java.util.List;

@Controller
public class StudentController {

    private final static Logger LOG = Logger.getLogger(StudentController.class);

    private final UserService userService;
    private final CourseService courseService;
    private final ArchiveService archiveService;

    @Autowired
    public StudentController(UserService userService, CourseService courseService, ArchiveService archiveService) {
        this.userService = userService;
        this.courseService = courseService;
        this.archiveService = archiveService;
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getAllUsers(Model model, Authentication auth,
                              @RequestParam(value = "error", required = false) String error ){
        if (error != null) {
            model.addAttribute("error", "An error occupied.");
        }
        if(auth.isAuthenticated()) {
            List<Course> allCourses = courseService.getAll();
            allCourses.forEach((c)->c.setUsers(userService.getStudentsFromCourse(c.getId())));
              model.addAttribute("courses",
                       allCourses);
           return "redirect:/";
        }else
            return"redirect:/";
    }
    @RequestMapping(value = "users/deleteStudent/{id}",method = RequestMethod.GET)
    public String deleteStudent(@PathVariable int id){
        userService.remove(id);
        LOG.debug(String.format("user with id %d successful removed", id));
        return "redirect:/";
    }

    @RequestMapping(value = "/markStudent",method = RequestMethod.POST)
    public String markStudent(@RequestParam(value = "mark")@NotNull int mark,
                              @RequestParam(value = "courseId")@NotNull int courseId,
                              @RequestParam(value = "studentId")@NotNull int studentId){
        archiveService.setMarkForStudent(courseId,studentId,mark);
        LOG.debug(String.format("Mark %d is set for user %d , course %d",mark,studentId,courseId));
        return "redirect:/";
    }
}

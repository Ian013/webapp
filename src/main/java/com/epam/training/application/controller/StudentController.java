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
            //User teacher = userService.getUserByEmail(auth.getName());
            List<Course> allCourses = courseService.getAll();
            allCourses.forEach((c)->c.setUsers(userService.getStudentsFromCourse(c.getId())));
            //List<Course> courses = courseService.getCoursesForTeacher(teacher.getId());
              model.addAttribute("courses",
                       allCourses);

           return "users";
        }else
            return"redirect:/";
    }
    @RequestMapping(value = "users/deleteStudent/{id}",method = RequestMethod.GET)
    public String deleteStudent(@PathVariable int id){
        userService.remove(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/markStudent",method = RequestMethod.POST)
    public String markStudent(@RequestParam(value = "mark") int mark,
                              @RequestParam(value = "courseId") int courseId,
                              @RequestParam(value = "studentId") int studentId){
        archiveService.setMarkForStudent(courseId,studentId,mark);
        return "redirect:/";
    }
}

package com.epam.training.application.controller;

import com.epam.training.application.domain.User;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentController {

    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public StudentController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getAllUsers(Model model){
        List<User> users = userService.getAll();
       // users.forEach((s)->s.setCourses(courseService.getCoursesForStudent(s.getId())));
        model.addAttribute("users", users);
        return "users";
    }

    /*  @RequestMapping(value="/registerPage", method=RequestMethod.GET)
      public String addStudent( ) {
          return "registerPage";
            /*
      @RequestMapping(value="/registerPage", method=RequestMethod.POST)
      public String registerStudent(Model model, @RequestParam String firstName) {
          User user = new User();
          user.setFirstName(firstName);
          userService.saveOrUpdate(user);
          return "registerPage";
      }
  */
    @RequestMapping(value = "/login-sucess",method = RequestMethod.GET)
    public String loginStudentSuccess(){
        return "login-sucess";
    }


}

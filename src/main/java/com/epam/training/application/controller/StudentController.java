package com.epam.training.application.controller;

import com.epam.training.application.domain.Student;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }
    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public String test(Model model){
        List<Student> students = studentService.getAll();
        students.forEach((s)->s.setCourses(courseService.getCoursesForStudent(s.getId())));
        model.addAttribute("students",students);
        return "students";
    }

}

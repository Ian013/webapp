package com.epam.training.application.controller;

import com.epam.training.application.domain.Course;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.TeacherService;
import com.epam.training.application.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
public class CourseController {

    private CourseService courseService;
    private final TeacherService teacherService;

    @Autowired
    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @RequestMapping(value = "/")
    public String index(){
        return "welcome";
    }

    @RequestMapping(value = "/courses",method = RequestMethod.GET)
    public String getCourses(Model model){
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        return "courses";
    }
    @RequestMapping(value="/add-course" , method = RequestMethod.GET)
    public String addCoursePage(){
        return "add-course";
    }

    @RequestMapping(value="/add-course", method = RequestMethod.POST)
    public String addNewCourse(@RequestParam(value = "title")String title,
                               @RequestParam(value = "startDate")Date start,
                               @RequestParam(value = "endDate")Date endDate,
                               @RequestParam(value = "teacherLastName")int teacherId){

        Course course = new Course(title,start, endDate,teacherService.getById(teacherId));

        courseService.saveOrUpdate(course);
        return "redirect:/courses";
    }

    @RequestMapping(value="delete/{id}", method=RequestMethod.GET)
    public String deleteItem(@PathVariable Integer id) {
        courseService.remove(id);
        return "redirect:/courses";
    }
}

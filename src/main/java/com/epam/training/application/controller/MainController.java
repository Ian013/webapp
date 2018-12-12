package com.epam.training.application.controller;

import com.epam.training.application.domain.Archive;
import com.epam.training.application.domain.User;
import com.epam.training.application.service.ArchiveService;
import com.epam.training.application.service.CourseService;
import com.epam.training.application.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/403",method = RequestMethod.GET)
    public String errorMapping()    {
        return "403";
    }

    @RequestMapping(value = "/")
    public String index(Model model){
        model.addAttribute("courses",courseService.getAll());
        return "index";
    }
    @RequestMapping(value = "/addMark/{studentId}+{courseId}", method = RequestMethod.POST)
    public String addMarkForStudent(@PathVariable int studentId,
                                    @PathVariable int courseId){

        archiveService.setMarkForStudent(courseId,studentId,0);
        return "index";
    }

}

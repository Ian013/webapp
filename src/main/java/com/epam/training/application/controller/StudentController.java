package com.epam.training.application.controller;

import com.epam.training.application.service.ArchiveService;
import com.epam.training.application.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@Controller
public class StudentController {

    private final static Logger LOG = Logger.getLogger(StudentController.class);

    private final UserService userService;
    private final ArchiveService archiveService;

    @Autowired
    public StudentController(UserService userService, ArchiveService archiveService) {
        this.userService = userService;
        this.archiveService = archiveService;
    }

  @RequestMapping(value = "users/deleteStudent/{id}",method = RequestMethod.GET)
    public String deleteStudent(@PathVariable int id){
        userService.remove(id);
        LOG.debug(String.format("user with id %d successful removed", id));
        return "forward:/";
    }

    @RequestMapping(value="deleteStudentFromCourse", method = RequestMethod.POST)
    public String deleteCourseForStudentPOST(@RequestParam(value = "studentId") int studentId,
                                             @RequestParam(value = "courseId") int courseId){
        userService.removeCourseForUser(studentId,courseId);
        LOG.debug(String.format("course num %d is removed for user num %d ",courseId, studentId));
        return "forward:/";
    }

    @RequestMapping(value = "/markStudent",method = RequestMethod.POST)
    public String markStudent(@RequestParam(value = "mark")@NotNull int mark,
                              @RequestParam(value = "courseId")@NotNull int courseId,
                              @RequestParam(value = "studentId")@NotNull int studentId){
        archiveService.setMarkForStudent(courseId,studentId,mark);
        LOG.debug(String.format("Mark %d is set for user %d , course %d",mark,studentId,courseId));
        return "forward:/";
    }
}

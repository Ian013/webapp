package com.epam.training.application.service;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends BasicService<User>, UserDetailsService {

    Integer addCourse(int studentId,int courseId);

    Integer removeCourseForUser(int studentId,int courseId);

    User getUserByEmail(String email);
    List<User> getAllStudents();
    List<User> getAllTeachers();
    List<User> getStudentsFromCourse(int courseId);
}

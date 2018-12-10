package com.epam.training.application.service;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;

import java.util.List;

public interface CourseService extends BasicService<Course> {

    List<Course> getCoursesForStudent(int studentId);
    Integer addStudent(int courseId, User user);


    List<Course> getCoursesForTeacher(int teacherId);
}

package com.epam.training.application.service;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.Student;

import java.util.List;

public interface CourseService extends BasicService<Course> {

    List<Course> getCoursesForStudent(int studentId);
    Integer addStudent(int courseId,Student student);

}

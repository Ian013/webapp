package com.epam.training.application.service;

import com.epam.training.application.dao.model.Course;

import java.util.List;

public interface CourseService {
    Course getCourse(int id);
    Integer addCourse(Course course);

    List<Course> getAllCourses();
    List<Course> getCoursesForStudent(int studentId);
}

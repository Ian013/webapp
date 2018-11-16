package com.epam.training.application.dao;

import com.epam.training.application.dao.model.Course;

import java.util.List;

public interface CourseDao {
    Integer addCourse(Course course);
    Course getCourse(int id);
    List<Course> getCourses();
}

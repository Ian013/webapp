package com.epam.training.application.service;

import com.epam.training.application.domain.Course;

import java.util.List;

public interface CourseService extends BasicService<Course> {

    List<Course> getCoursesForStudent(int studentId);

    List<Course> getCoursesForTeacher(int teacherId);
}

package com.epam.training.application.dao;

import com.epam.training.application.domain.Course;

import java.util.List;

public interface CourseDao extends BasicDao<Course> {

    List<Course> getCoursesByStudentId(int studentId);
    List<Course> getCoursesForTeacher(int teacherId);
}

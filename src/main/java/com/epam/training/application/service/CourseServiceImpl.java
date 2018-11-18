package com.epam.training.application.service;

import com.epam.training.application.dao.jbdc.JdbcTemplateCourseDao;
import com.epam.training.application.dao.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class CourseServiceImpl implements CourseService{
    //@Autowired
    private JdbcTemplateCourseDao courseDao;

    @Override
    public Course getCourse(int id) {
        return courseDao.getCourse(id);
    }

    @Override
    public Integer addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getCourses();
    }

    @Override
    public List<Course> getCoursesForStudent(int studentId) {
        return courseDao.getCoursesByStudentId(studentId);
    }
}

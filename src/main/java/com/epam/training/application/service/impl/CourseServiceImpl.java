package com.epam.training.application.service.impl;

import com.epam.training.application.dao.CourseDao;
import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;
import com.epam.training.application.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course getById(int id) {
        return courseDao.getById(id);
    }

    @Override
    public Integer saveOrUpdate(Course course) {
        if(course!=null) {
            return courseDao.saveOrUpdate(course);
        }else throw new NullPointerException("A NULL VALUE OF THE COURSE");
    }

    @Override
    public Integer remove(int id) {
        return courseDao.remove(id);
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public List<Course> getCoursesForStudent(int studentId) {
        return courseDao.getCoursesByStudentId(studentId);
    }

    @Override
    public Integer addStudent(int courseId, User user) {
        Course course = courseDao.getById(courseId);
        List<User> users =course.getUsers();
        users.add(user);
        course.setUsers(users);
        return user.getId();
    }
}

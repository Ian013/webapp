package com.epam.training.application.service.impl;

import com.epam.training.application.dao.UserDao;
import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;
import com.epam.training.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getById(int id) {
        if(id>0){
            return userDao.getById(id);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Integer saveOrUpdate(User user) {
        if(user !=null){
            return userDao.saveOrUpdate(user);
        }
        return null;
    }

    @Override
    public Integer remove(int id) {
        if(id>0){
        return userDao.remove(id);
        }throw new NullPointerException();
    }

    @Override
    public Integer addCourse(int studentId,Course course) {
        User user = userDao.getById(studentId);
        List<Course> courses = user.getCourses();
        courses.add(course);
        user.setCourses(courses);
        return course.getId();
    }

    @Override
    public User getUserByEmail(String email) {
        if(email!=null){
        return userDao.getUserByEmail(email);
        }else throw new IllegalArgumentException("Email is null!");
    }
}

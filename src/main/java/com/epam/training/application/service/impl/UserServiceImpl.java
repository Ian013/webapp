package com.epam.training.application.service.impl;

import com.epam.training.application.dao.UserDao;
import com.epam.training.application.domain.User;
import com.epam.training.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public Integer addCourse(int studentId,int courseId) {

        return userDao.addCourse(studentId,courseId);
    }

    @Override
    public Integer removeCourseForUser(int studentId,int courseId) {
          return userDao.removeCourseForStudent(studentId, courseId);
    }

    @Override
    public User getUserByEmail(String email) {
        if(email!=null){
        return userDao.getUserByEmail(email);
        }else throw new IllegalArgumentException("Email is null!");
    }

    @Override
    public List<User> getAllStudents() {
        return userDao.getAllStudents();
    }

    @Override
    public List<User> getAllTeachers() {
        return userDao.getAllTeachers();
    }

    @Override
    public List<User> getStudentsFromCourse(int courseId) {
        return userDao.getStudentsFromCourse(courseId);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}

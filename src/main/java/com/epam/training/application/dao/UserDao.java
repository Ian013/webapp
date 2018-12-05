package com.epam.training.application.dao;

import com.epam.training.application.domain.User;

import java.util.List;

public interface UserDao extends BasicDao<User>{

    List<User> getStudentsFromCourse(int courseId);

    List<User> getAllStudents();
    List<User> getAllTeachers();

    Integer addCourse(int studentId,int courseId);
}

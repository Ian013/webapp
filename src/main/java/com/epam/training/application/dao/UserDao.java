package com.epam.training.application.dao;

import com.epam.training.application.domain.User;

import java.util.List;

public interface UserDao extends BasicDao<User>{

    List<User> getStudentsFromCourse(int courseId);

    User getUserByEmail(String email);
    List<User> getAllStudents();
    List<User> getAllTeachers();

   // List<Role> getRolesForUser(int id);

    Integer addCourse(int studentId,int courseId);
}

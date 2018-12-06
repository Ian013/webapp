package com.epam.training.application.service;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.User;

public interface UserService extends BasicService<User> {

    Integer addCourse(int studentId,Course course);
    User getUserByEmail(String email);
}

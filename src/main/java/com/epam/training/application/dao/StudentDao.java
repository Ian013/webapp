package com.epam.training.application.dao;

import com.epam.training.application.domain.Student;

import java.util.List;

public interface StudentDao extends BasicDao<Student>{


    List<Student> getStudentsFromCourse(int courseId);
}

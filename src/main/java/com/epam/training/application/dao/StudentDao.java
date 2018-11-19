package com.epam.training.application.dao;

import com.epam.training.application.domain.Student;

import java.util.List;

public interface StudentDao extends BasicDao<Student>{
    Integer addStudent(Student student);

    Student getStudent(Integer id);

    List<Student> getAllStudents();

    List<Student> getStudentsFromCourse(int courseId);
}

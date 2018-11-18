package com.epam.training.application.dao;

import com.epam.training.application.dao.model.Student;

import java.util.List;

public interface StudentDao {
    Integer addStudent(Student student);

    Student getStudent(Integer id);

    List<Student> getAllStudents();

    List<Student> getStudentsFromCourse(int courseId);
}

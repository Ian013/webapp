package com.epam.training.application.service;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.Student;

public interface StudentService extends BasicService<Student> {

    Integer addCourse(int studentId,Course course);
}

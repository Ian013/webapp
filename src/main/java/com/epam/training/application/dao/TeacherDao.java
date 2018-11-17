package com.epam.training.application.dao;

import com.epam.training.application.dao.model.Teacher;

import java.util.List;

public interface TeacherDao {

    Integer addTeacher(Teacher teacher);

    Teacher getTeacher(long id);

    List<Teacher> getTeachers();

    Integer updateTeacher(int id, Teacher teacher);


}

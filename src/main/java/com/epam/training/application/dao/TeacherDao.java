package com.epam.training.application.dao;

import com.epam.training.application.domain.Teacher;

import java.util.List;

@Deprecated
public interface TeacherDao  extends BasicDao<Teacher>{

    List<Teacher> getByLastName(String lastName);


}

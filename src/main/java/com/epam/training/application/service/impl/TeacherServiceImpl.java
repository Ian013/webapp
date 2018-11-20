package com.epam.training.application.service.impl;

import com.epam.training.application.dao.TeacherDao;
import com.epam.training.application.dao.jbdc.TeacherDaoImpl;
import com.epam.training.application.domain.Teacher;
import com.epam.training.application.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public Teacher getById(int id) {
        if(id>0){
        return teacherDao.getById(id);
        }else {
            throw new InvalidParameterException("Teacher's ID can't be negative!");
        }
    }

    @Override
    public List<Teacher> getAll() {

        return teacherDao.getAll();
    }

    @Override
    public Integer saveOrUpdate(Teacher teacher) {
        if(teacher!=null) {
            return teacherDao.saveOrUpdate(teacher);
        }else throw new NullPointerException("");
    }

    @Override
    public Integer remove(int id) {
        if(id>0){
            return teacherDao.remove(id);
        }else {
            throw new InvalidParameterException("Teacher's ID can't be negative!");
        }
    }
}

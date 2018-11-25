package com.epam.training.application.service.impl;

import com.epam.training.application.dao.StudentDao;
import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.Student;
import com.epam.training.application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student getById(int id) {
        if(id>0){
            return studentDao.getById(id);
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public Integer saveOrUpdate(Student student) {
        if(student!=null){
            return studentDao.saveOrUpdate(student);
        }
        return null;
    }

    @Override
    public Integer remove(int id) {
        if(id>0){
        return studentDao.remove(id);
        }throw new NullPointerException();
    }

    @Override
    public Integer addCourse(int studentId,Course course) {
        Student student = studentDao.getById(studentId);
        List<Course> courses = student.getCourses();
        courses.add(course);
        student.setCourses(courses);
        return course.getId();
    }
}

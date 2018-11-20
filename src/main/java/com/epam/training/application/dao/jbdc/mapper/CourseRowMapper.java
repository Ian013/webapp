package com.epam.training.application.dao.jbdc.mapper;

import com.epam.training.application.domain.Course;
import com.epam.training.application.domain.Teacher;
import com.epam.training.application.service.TeacherService;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int teacherId = resultSet.getInt("teacher_id");
        Date startDate = (Date) resultSet.getObject("startDate");
        Date endDate = (Date)resultSet.getObject("endDate");
        String teacherFirstName = resultSet.getString("firstName");
        String teacherLastName = resultSet.getString("lastName");
        Teacher teacher = new Teacher(teacherId,teacherFirstName,teacherLastName);

        return new Course(id,name,startDate,endDate,teacher);
    }
    //"SELECT course.id,course.name,startDate,endDate,teacher.firstName,teacher.lastName"

}

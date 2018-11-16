package com.epam.training.application.dao.jdbc;

import com.epam.training.application.dao.CourseDao;
import com.epam.training.application.dao.jdbc.connections.ConnectionPool;
import com.epam.training.application.dao.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public Integer addCourse(Course course) {
        try(Connection con  = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pr = con.prepareStatement(
                    "INSERT INTO course(name,teacher_id,startDate,endDate) VALUES (?,?,?,?);");
            pr.setString(1,course.getName());
            pr.setInt(2,course.getTeacher().getId());
            pr.setObject(3,course.getStartDate());
            pr.setObject(4,course.getEndDate());
            pr.executeQuery();
        } catch (SQLException e) {
            //log.error(e.getMessage());
            e.printStackTrace();
        }
        return course.getId();
    }

    @Override
    public Course getCourse(int id) {
        return null;
    }

    @Override
    public List<Course> getCourses() {
        return null;
    }
}

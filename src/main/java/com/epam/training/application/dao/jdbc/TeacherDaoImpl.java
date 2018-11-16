package com.epam.training.application.dao.jdbc;

import com.epam.training.application.dao.TeacherDao;
import com.epam.training.application.dao.jdbc.connections.ConnectionPool;
import com.epam.training.application.dao.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {

    //private final static Logger log = Logger.getLogger(TeacherDaoImpl.class);

    @Override
    public Integer addTeacher(Teacher teacher) {
        try(Connection con  = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pr = con.prepareStatement("INSERT INTO teacher(firstName, lastName) VALUES(?,?);");
            pr.setString(1,teacher.getFirstName());
            pr.setString(2,teacher.getLastName());
            pr.executeQuery();
        } catch (SQLException e) {
            //log.error(e.getMessage());
            e.printStackTrace();
        }
        return teacher.getId();
    }

    @Override
    public Teacher getTeacher(long id) {
        Teacher teacher = new Teacher();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pr = con.prepareStatement("SELECT * FROM teacher WHERE id = ?");
            pr.setInt(1,(int)id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                teacher.setId(rs.getInt("id"));
                teacher.setFirstName(rs.getString("firstName"));
                teacher.setLastName(rs.getString("lastName"));
            }
        }catch (SQLException e) {

            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public List<Teacher> getTeachers() {
        ArrayList<Teacher> teachers =new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pr = con.prepareStatement("SELECT * FROM teacher");
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                teachers.add(
                        new Teacher(rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public Integer updateTeacher(long id, Teacher teacher) {
        return null;
    }
}

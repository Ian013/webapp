package com.epam.training.application.dao.jdbc;

import com.epam.training.application.dao.StudentDao;
import com.epam.training.application.dao.jdbc.connections.ConnectionPool;
import com.epam.training.application.dao.model.Student;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
private static final Logger log = Logger.getLogger(StudentDaoImpl.class);

    @Override
    public Integer addStudent(Student student) {
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pr =con.prepareStatement("INSERT INTO student(firstName,lastName) VALUES (?,?)");
            pr.setString(1,student.getFirstName());
            pr.setString(2,student.getLastName());

        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return student.getId();
    }

    @Override
    public Student getStudent(Integer id) {
        Student student = null;
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pr = con.prepareStatement("SELECT id,firstName,lastName FROM student WHERE id =?" );
            pr.setInt(1,id);
            ResultSet res = pr.executeQuery();
            if(res.next()) {
                student = new Student();
                student.setId(res.getInt("id"));
                student.setFirstName(res.getString("firstName"));
                student.setLastName(res.getString("lastName"));
            }
        }catch(SQLException e){
            log.error(e.getMessage());
        }
        return student;
    }

    @Override
    public List<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pr = con.prepareStatement("SELECT * FROM student" );
            ResultSet res = pr.executeQuery();
            while(res.next()) {
                students.add(new Student(res.getInt("id")
                        ,res.getString("firstName")
                        ,res.getString("lastName")));
            }
        }catch(SQLException e){
            log.error(e.getMessage());
        }
        return students;
    }
}

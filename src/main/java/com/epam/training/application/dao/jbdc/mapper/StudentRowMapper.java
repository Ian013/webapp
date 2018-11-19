package com.epam.training.application.dao.jbdc.mapper;

import com.epam.training.application.domain.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {


    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {

        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");

        return new Student(id,firstName,lastName);
    }
}

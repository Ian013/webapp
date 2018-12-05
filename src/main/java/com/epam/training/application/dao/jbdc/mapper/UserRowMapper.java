package com.epam.training.application.dao.jbdc.mapper;

import com.epam.training.application.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");

        String email = resultSet.getString("email");

        return new User(id,firstName,lastName);
    }
}

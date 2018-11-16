package com.epam.training.application.dao.jbdc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training.application.dao.model.User;

public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rawNumber) throws SQLException {

		String fName = rs.getString("FIRST_NAME");
		String lName = rs.getString("LAST_NAME");
		Integer id = rs.getInt("USER_ID");

		return new User(id, fName, lName);
	}

}

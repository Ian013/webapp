package com.epam.training.application.dao.jbdc;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.training.application.dao.UserDao;
import com.epam.training.application.dao.jbdc.mapper.UserRowMapper;
import com.epam.training.application.dao.model.User;

@Transactional
@Repository
public class JbdcTemplateUserDao implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public JbdcTemplateUserDao(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public Integer insertUser(User user) {
		KeyHolder holder = new GeneratedKeyHolder();

		String sql = "INSERT INTO CUSTOM_USER (FIRST_NAME, LAST_NAME) values (?, ?)";
		jdbcTemplate.update((connection) -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			return ps;
		}, holder);

		return Integer.valueOf(holder.getKeys().get("user_id").toString());
	}

	public User getUser(Integer id) {
		String sql = "SELECT * FROM CUSTOM_USER WHERE CUSTOM_USER.USER_ID = ?";

		User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
		return user;
	}

	@Override
	public List<User> getUsers(String firstName) {
		String sql = "SELECT * FROM CUSTOM_USER WHERE CUSTOM_USER.FIRST_NAME = ?";

		return jdbcTemplate.query(sql, new UserRowMapper(), firstName);
	}

}

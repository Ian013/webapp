package com.epam.training.application.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.epam.training.application.dao.UserDao;
import com.epam.training.application.dao.model.User;

@Configuration
@PropertySource("classpath:application.properties")
public class ContextConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "my.db", name = "name", havingValue = "pg", matchIfMissing = false)
	public DataSource ds() {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("org.postgresql.Driver");
		driver.setUrl("jdbc:postgresql://localhost:5432/jdbcsample");
		driver.setUsername("jdbc-test");
		driver.setPassword("jdbc-test");
		return driver;

	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		return jdbcTemplate;
	}

	@Bean
	public String thisWorks(UserDao userDao) {
		Integer insertUser = userDao.insertUser(new User("Max", "Naumovich"));
		System.out.println("Our saved user: " + userDao.getUser(insertUser));

		userDao.getUsers("Max").forEach(u -> System.out.println("User :" + u.toString()));

		return "Or not?";
	}

}
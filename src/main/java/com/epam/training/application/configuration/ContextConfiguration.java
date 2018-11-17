package com.epam.training.application.configuration;

import javax.sql.DataSource;

import com.epam.training.application.dao.StudentDao;
import com.epam.training.application.dao.model.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@PropertySource("classpath:application.properties")
public class ContextConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "my.db", name = "name", havingValue = "pg")
	public DataSource ds() {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("org.postgresql.Driver");
		driver.setUrl("jdbc:mysql://localhost:3306/faculty");
		driver.setUsername("admin");
		driver.setPassword("001201313");
		return driver;

	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		return jdbcTemplate;
	}

	@Bean
	public String thisWorks(StudentDao studentDao) {
		Integer insertUser = studentDao.addStudent(new Student("Max", "Naumovich"));
		System.out.println("Our saved user: " + userDao.getUser(insertUser));

		userDao.getUsers("Max").forEach(u -> System.out.println("User :" + u.toString()));

		return "Or not?";
	}

}

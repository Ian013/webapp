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
	@ConditionalOnProperty(prefix = "my.db", name = "name", havingValue = "pg",matchIfMissing = false)
	public DataSource ds() {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		//driver.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driver.setUrl("jdbc:mysql://localhost:3306/faculty?serverTimezone=UTC");
		driver.setUsername("admin");
		driver.setPassword("001201313");
		return driver;

	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean
	public String thisWorks(StudentDao studentDao) {
		try {
			Integer insertUser = studentDao.addStudent(new Student("Sample", "Text"));
			System.out.println("Our saved student: " + studentDao.getStudent(insertUser));

			studentDao.getStudents().forEach(u -> System.out.println("Student :" + u.toString()));
		}catch(NullPointerException e){
		    e.printStackTrace();
		}
		return "Or not?";
	}

}

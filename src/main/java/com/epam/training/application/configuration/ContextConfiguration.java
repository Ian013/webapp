package com.epam.training.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration

@PropertySource(value= {"classpath:dataSource.properties"})
public class ContextConfiguration {

	private final Environment env;

	@Autowired
	public ContextConfiguration(Environment env) {
		this.env = env;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setUrl(env.getProperty("ds.url"));
		driver.setUsername(env.getProperty("ds.username"));
		driver.setPassword(env.getProperty("ds.password"));

		return driver;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}

package com.epam.training.application.configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Configuration

@PropertySource(value= {"classpath:dataSource.properties"})
public class ContextConfiguration {
	private final static Logger LOG = Logger.getLogger(ContextConfiguration.class);

	private final Environment env;

	@Autowired
	public ContextConfiguration( Environment env) {
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


	public void createDatabaseIfNotExist(DataSource dataSource){
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.addScript(new ClassPathResource(
				"/resources/mysqlScripts/createDatabaseIfNotExist.sql"));
		try {
			Connection connection = dataSource.getConnection();
			rdp.populate(connection); // this starts the script execution, in the order as added
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}

	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {

		return new JdbcTemplate(dataSource);
	}
}

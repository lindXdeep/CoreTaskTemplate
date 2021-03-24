package io.lindx.task.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Application Configuration.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
@Configuration
@PropertySource("classpath:db.properties")
public class AppConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setDriverClassName(environment.getProperty("db.driver"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));
		return dataSource;
	}
}

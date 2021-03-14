package io.lindx.task.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.lindx.task.model.User;

/**
 * Hibernate Configuration.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
@Configuration
@PropertySource("classpath:hiber.properties")
@EnableTransactionManagement
public class ConfigHibernate {

	@Autowired
	private Environment environment;

	@Autowired
	private DataSource dataSource;

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {

		Properties properties = new Properties();
		properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);

		factoryBean.setAnnotatedClasses(User.class);

		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());

		return transactionManager;
	}

}

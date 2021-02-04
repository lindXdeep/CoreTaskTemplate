package jm.task.core.hiber.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jm.task.core.hiber.model.Car;
import jm.task.core.hiber.model.User;

/**
 * AppConfig
 */
@Configuration
@PropertySources(
    {
        @PropertySource("classpath:db.properties"),
        @PropertySource("classpath:hiber.properties")
    }
)
@ComponentScan(value = "jm.task.core.hiber")
@EnableTransactionManagement
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName(environment.getProperty("db.driver"));
                dataSource.setUrl(environment.getProperty("db.url"));
                dataSource.setUsername(environment.getProperty("db.username"));
                dataSource.setPassword(environment.getProperty("db.password"));

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){

        Properties properties = new Properties();
                properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
                properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
                factoryBean.setDataSource(getDataSource());
                factoryBean.setHibernateProperties(properties);

                factoryBean.setAnnotatedClasses(User.class);
                factoryBean.setAnnotatedClasses(Car.class);

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
            transactionManager.setSessionFactory(getSessionFactory().getObject());
        
        return transactionManager;
    }
}
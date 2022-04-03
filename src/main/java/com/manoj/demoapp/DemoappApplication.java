package com.manoj.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.DispatcherServlet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.jdbc.DataSourceBuilder;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoappApplication.class, args);
	}

	@Bean
	public DataSource getDataSource() 
    {
		
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver"); 
        dataSourceBuilder.url("jdbc:mysql://remotemysql.com/dEy0EJBxLw");
        dataSourceBuilder.username("dEy0EJBxLw");
        dataSourceBuilder.password("UEZbjZWMCY");
        return dataSourceBuilder.build();
	}

	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		//sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		hibernateProperties.put("hibernate.show_sql", false);
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	

	


}

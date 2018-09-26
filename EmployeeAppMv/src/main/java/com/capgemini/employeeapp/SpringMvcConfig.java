package com.capgemini.employeeapp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ViewResolverComposite;

@Configuration
@EnableWebMvc // equivalent to mvc annotation driven
@ComponentScan(basePackages = { "com.capgemini.employeeapp" })
//@PropertySource("classpath:application.properties")
public class SpringMvcConfig {
	/*@Value("${DB_URL}")
	private String dburl;

	@Value("${DB.USERNAME}")
	private String username;

	@Value("${DB.PASSWORD}")
	private String password;

	@Value("${path}")
	private String path;
*/
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/*dataSource.setDriverClassName(path);
		dataSource.setUrl(dburl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);*/
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/kavya");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate;
	}

	@Bean
	public ViewResolver getInternalResourceViewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}

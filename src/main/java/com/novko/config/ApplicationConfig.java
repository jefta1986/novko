package com.novko.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.novko.internal", "com.novko.api", "com.novko.config" })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.novko.internal")
@EnableAspectJAutoProxy
@EnableCaching
public class ApplicationConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://ec2-54-246-121-32.eu-west-1.compute.amazonaws.com:5432/dddbgpe8ehvb33");
		ds.setUsername("hkorohvqibwing");
		ds.setPassword("5abc5c62dcccf455437ebd6df076b7511cd02994b91220494f4e73be6cb95fd6");
//		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		ds.setSchema("sch_novko");
// 		ds.setUrl("jdbc:postgresql://localhost:5432/sch_novko");
// 		ds.setUsername("novko");
// 		ds.setPassword("novko");
		return ds;
	}

	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
//		adapter.setDatabase(Database.MYSQL);
		adapter.setDatabase(Database.POSTGRESQL);
		
		Properties props = new Properties();
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource());
		emfb.setPackagesToScan("com.novko.internal");
		emfb.setJpaProperties(props);
		emfb.setJpaVendorAdapter(adapter);
		return emfb;
	}
	
	
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
//		lsfb.setDataSource(dataSource());
//		lsfb.setPackagesToScan("com.vecera.internal");
//		Properties props = new Properties();
//		props.setProperty("hibernate.format_sql", "true");
//		props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//		lsfb.setHibernateProperties(props);
//		return lsfb;
//	}
//	
//


//	@Bean
//	public PlatformTransactionManager transactionManager(SessionFactory sf) {
//		return new HibernateTransactionManager(sf);
//	}
	
	
//	@Bean
//	public HibernateJpaSessionFactoryBean sessioFactory(EntityManagerFactory emf) {
//		HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
//		factory.setEntityManagerFactory(emf);
//		return factory;
//	}
	
	
	
	
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return  new JpaTransactionManager(emf);	
	}
	
	
	
	
//	@Bean
//	public RestaurantRepository jdbcRestaurantRepository() {
//		JdbcRestaurantRepository rr = new JdbcRestaurantRepository();
//		rr.setDataSource(dataSource());
//		
//		return rr;
//	}
	
	
	
//	
//	@Bean
//	public RestaurantRepository JdbcTempRestaurantRepository() {
//		JdbcTempRestaurantRepository rr = new JdbcTempRestaurantRepository();
//		rr.setDataSource(dataSource());
//		
//		return rr;
//	}
	
	
}

package com.novko.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.ehcache.core.EhcacheManager;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
@ComponentScan(basePackages = {"com.novko.internal", "com.novko.api", "com.novko.config", "com.novko.security"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.novko.internal", "com.novko.security", "com.novko.pdf"})
@EntityScan(basePackages = {"com.novko.internal", "com.novko.security", "com.novko.pdf"})
//@EnableAspectJAutoProxy
//@EnableCaching
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
		
//  		ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
//  		ds.setUsername("postgres");
//  		ds.setPassword("postgres");
		return ds;
	}

	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
//		adapter.setDatabase(Database.MYSQL);
		adapter.setDatabase(Database.POSTGRESQL);
		
		Properties props = new Properties();
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("spring.jpa.generate-ddl", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "none");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
//		props.setProperty("hibernate.default.schema", "sch_novko");
		props.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");

		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setJpaVendorAdapter(adapter);
		emfb.setPackagesToScan(new String[]{"com.novko.internal", "com.novko.security", "com.novko.pdf"});
		emfb.setDataSource(dataSource());
		emfb.setJpaProperties(props);
		emfb.afterPropertiesSet();

		return emfb.getObject();
	}
	

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManaget =  new JpaTransactionManager(emf);
		txManaget.setEntityManagerFactory(entityManagerFactory());
		return txManaget;
	}


	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("novko49@gmail.com");
		mailSender.setPassword("Nov@k1949");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");  //opciono za debug mode

		return mailSender;
	}

	//	@Bean
//	public CacheManager cacheManager(){
//		EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
//		ehCacheManager.setCacheManager(ehCacheManager().getObject());
//		return ehCacheManager;
//	}
//
//
//	@Bean
//	public EhCacheManagerFactoryBean ehCacheManager(){
//		EhCacheManagerFactoryBean ehCachefactoryBean = new EhCacheManagerFactoryBean();
//		ehCachefactoryBean.setConfigLocation(new ClassPathResource("classpath*:/configxml/ehcache.xml"));
//		ehCachefactoryBean.setShared(true);
//		return  ehCachefactoryBean;
//	}




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



	
}

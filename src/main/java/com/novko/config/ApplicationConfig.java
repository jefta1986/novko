package com.novko.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.novko.internal", "com.novko.api", "com.novko.config", "com.novko.security"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.novko.internal", "com.novko.security", "com.novko.pdf"})
@EntityScan(basePackages = {"com.novko.internal", "com.novko.security", "com.novko.pdf"})
@EnableAsync
//@EnableAspectJAutoProxy
//@EnableCaching
public class ApplicationConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");

        ds.setUrl("jdbc:postgresql://ec2-54-78-127-245.eu-west-1.compute.amazonaws.com:5432/dc9ihbk205tp76");
        ds.setUsername("oibquhcibuhqzs");
        ds.setPassword("d3bdcdcc82f3b88d68198b1551c9dd8006446090a1b77dad13ff406fc1da2f49");
//        ds.setUrl("jdbc:postgresql://db-postgresql-nyc1-84649-do-user-7801116-0.b.db.ondigitalocean.com:25060/defaultdb");
//		ds.setUsername("doadmin");
//		ds.setPassword("xitr8qvmofja425m");

//		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		ds.setSchema("sch_novko");

//        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
//        ds.setUsername("novko");
//        ds.setPassword("novko");
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
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
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
        JpaTransactionManager txManaget = new JpaTransactionManager(emf);
        txManaget.setEntityManagerFactory(entityManagerFactory());
        return txManaget;
    }


//    @Bean
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }


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


//    @Bean
//    public MyRememberMeServices myRememberMeServices(){
//        MyRememberMeServices service = new MyRememberMeServices (REMEMBERME_KEY, formUserDetailsService);
//        service.setAlwaysRemember(true);
//        service.setCookieName("xxxx");
//        service.setParameter("_spring_security_remember_me");
//        service.setTokenValiditySeconds(123);
//        return service;
//    };

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

    //    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

    //    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }

}

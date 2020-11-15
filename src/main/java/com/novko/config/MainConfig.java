package com.novko.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@ImportResource({"classpath*:/configxml/security-config.xml"})
@Import({ApplicationConfig.class, MvcConfig.class, SwaggerConfig.class})
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainConfig {
	
	
}

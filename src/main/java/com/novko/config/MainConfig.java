package com.novko.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Import({ApplicationConfig.class, MvcConfig.class, SwaggerConfig.class})
@ImportResource({"classpath*:/configxml/customerProfiling-context-security.xml"})
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainConfig {
	
	
}

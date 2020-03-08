package com.novko.config;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@Import(ApplicationConfig.class)
@ImportResource({"classpath*:/configxml/customerProfiling-context-security.xml"})
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainConfig {
	
	
}

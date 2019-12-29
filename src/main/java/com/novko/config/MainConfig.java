package com.novko.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({ApplicationConfig.class, SecurityConfig.class})
public class MainConfig {

}

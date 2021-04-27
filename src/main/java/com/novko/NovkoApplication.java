package com.novko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.novko.config.MainConfig;

@SpringBootApplication
@Import({ MainConfig.class })
public class NovkoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NovkoApplication.class, args);
	}

}

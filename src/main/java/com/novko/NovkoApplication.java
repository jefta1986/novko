package com.novko;

import com.novko.security.ApplicationRoles;
import com.novko.security.User;
import com.novko.security.UserLanguage;
import com.novko.security.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.novko.config.MainConfig;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Import({ MainConfig.class })
public class NovkoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NovkoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		return (args) -> {
//
//		User user = new User();
//        user.setUsername("novko@gmail.com");
//			user.setPassword(passwordEncoder.encode("novko"));
//			user.setActive(true);
//			user.setRole(ApplicationRoles.ROLE_ADMIN.getRole());
//        user.setRabat(new Double(0.00));
//        user.setCode("ccc");
//        user.setFirma("green land");
//        user.setGrad("sd");
//        user.setUlica("centar");
//        user.setPib("pib");
//        user.setMb("mb");
//			user.setLanguage(UserLanguage.SR.getLanguage());
//
//			userRepository.save(user);
//
//		};
//	}

}

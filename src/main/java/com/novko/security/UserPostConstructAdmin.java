package com.novko.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserPostConstructAdmin {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserPostConstructAdmin(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    void init() {
        User user = new User();
        user.setUsername("novko@gmail.com");
        user.setPassword(passwordEncoder.encode("novko"));
        user.setActive(true);
        user.setRole(ApplicationRoles.ROLE_ADMIN.getRole());
//        user.setRabat(userRequest.getRabat());
//        user.setCode(userRequest.getCode());
        user.setFirma("Green Land");
        user.setGrad("Smederevo");
//        user.setUlica(userRequest.getUlica());
//        user.setPib(userRequest.getPib());
//        user.setMb(userRequest.getMb());
        user.setLanguage(UserLanguage.SR.getLanguage());

        userService.save(user);
    }
}

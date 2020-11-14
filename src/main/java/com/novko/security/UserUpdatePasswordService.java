package com.novko.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userUpdatePasswordService")
public class UserUpdatePasswordService {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserUpdatePasswordService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public void update(User userRequest) {
        User user = userService.findById(userRequest.getId());
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }

}

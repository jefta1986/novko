package com.novko.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("userUpdatePasswordService")
public class UserUpdatePasswordService {


    private JpaUserDao jpaUserDaoImpl;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setJpaUserDaoImpl(JpaUserDao jpaUserDaoImpl) {
        this.jpaUserDaoImpl = jpaUserDaoImpl;
    }



    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public void update(User user){
//        Optional<User> opt = jpaUserRepository.findByUsername(user.getUsername());
//
//        if(!opt.isPresent()) {
//            throw new IllegalArgumentException("username doesn't exists");
//        }
//        User userDb = opt.get();
//        userDb.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }



}

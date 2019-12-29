package com.novko.api;


import com.novko.security.JpaUserRepository;
import com.novko.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private JpaUserRepository jpaUserRepository;


    @GetMapping(value = "/")
    public String home() {
        return "<h1>Home Page</h1>";
    }


    @GetMapping(value = "/user")
    public String user() {
        return "<h1>User Page</h1>";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "<h1>Admin Page</h1>";
    }

    @PostMapping(value = "/")
    public String createUser(@RequestBody User user){
        jpaUserRepository.saveAndFlush(user);
        return "ok";
    }


}

package com.novko.api;


import com.novko.security.ApplicationRoles;
import com.novko.security.JpaUserRepository;
import com.novko.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private JpaUserRepository jpaUserRepository;
    private AuthenticationManager authenticationManager;
    private UserDetailsService myUserDetailsService;
    private PasswordEncoder passwordEncoder;


//    @Autowired
//    public UserController(JpaUserRepository jpaUserRepository, AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//        this.jpaUserRepository = jpaUserRepository;
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }

        @Autowired
    public void setJpaUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }


    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping(value = "/registration")
    public ResponseEntity<String> registration(@RequestBody User user, @RequestParam ApplicationRoles role){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        switch (role){
            case ROLE_USER:
                user.setRole(ApplicationRoles.ROLE_USER.getRole());
                break;
            case ROLE_ADMIN:
                user.setRole(ApplicationRoles.ROLE_ADMIN.getRole());
                break;
        }
        jpaUserRepository.saveAndFlush(user);

        return new ResponseEntity<String>("added User: ", HttpStatus.OK);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody User user){

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        SecurityContext sc = SecurityContextHolder.getContext();

        Authentication authentication = authenticationManager.authenticate(token);

        sc.setAuthentication(authentication);

        return new ResponseEntity<String>("User authenticated ", HttpStatus.OK);
    }







}

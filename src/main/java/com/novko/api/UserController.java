package com.novko.api;


import com.novko.security.JpaUserRepository;
import com.novko.security.MyUserDetails;
import com.novko.security.MyUserDetailsService;
import com.novko.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/users")
public class UserController {

    private JpaUserRepository jpaUserRepository;
    private AuthenticationManager authenticationManager;
    private MyUserDetailsService userDetailsService;
//    private PasswordEncoder passwordEncoder;


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
    public void setUserDetailsService(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    @Autowired
//    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @PostMapping(value = "/")
    public ResponseEntity<String> createUser(@RequestBody User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        jpaUserRepository.saveAndFlush(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        if(userDetails==null) new ResponseEntity<String>("user doesn't exists", HttpStatus.OK);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        SecurityContext sc = SecurityContextHolder.getContext();

        Authentication authentication = authenticationManager.authenticate(token);

//        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);


        return new ResponseEntity<String>("Authenticated user with: " + authentication.getPrincipal() + authentication.getName(), HttpStatus.OK);
    }


    @PostMapping(value = "/ubaci")
    public ResponseEntity<String> authenticateUser(@RequestParam String username){
//        MyUserDetails userDetails = new MyUserDetails(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails==null) new ResponseEntity<String>("user doesn't exists", HttpStatus.OK);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);


        return new ResponseEntity<String>("Authenticated user with: " + authentication.getPrincipal() + authentication.getName(), HttpStatus.OK);
    }







}

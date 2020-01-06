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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;


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
    public ResponseEntity<String> registration(@RequestBody User user, @RequestParam ApplicationRoles role) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        switch (role) {
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


//    @PostMapping(value = "/login")
    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        request.getSession();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map( a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.joining("" ));

        return new ResponseEntity<String>( role , HttpStatus.OK);
    }




    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout() {

        return new ResponseEntity<String>("Logout! ", HttpStatus.OK);
    }




//    @GetMapping(value = "/izadji")
//    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if( authentication != null) new SecurityContextLogoutHandler().logout(request, response, authentication);
//
//        clearCookie(request, response);
//
//        return new ResponseEntity<String>("Logout ", HttpStatus.OK);
//    }
//
//
//
//    private void clearCookie(HttpServletRequest request, HttpServletResponse response){
//        String cookieName = "remembeMe";
//        Cookie cookie = new Cookie(cookieName, null);
//        cookie.setMaxAge(0);
//        cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/" );
//        response.addCookie(cookie);
//    }


}

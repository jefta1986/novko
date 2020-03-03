package com.novko.api;


import com.novko.pdf.EmailService;
import com.novko.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
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

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class UserController {

    private JpaUserDao jpaUserDaoImpl;
    private AuthenticationManager authenticationManager;
    private UserDetailsService myUserDetailsService;
    private PasswordEncoder passwordEncoder;
    private EmailService emailServiceImpl;
    private UserUpdatePasswordService userUpdatePasswordService;


//    @Autowired
//    public UserController(JpaUserRepository jpaUserRepository, AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//        this.jpaUserRepository = jpaUserRepository;
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Autowired
    public void setJpaUserDaoImpl(JpaUserDao jpaUserDaoImpl) {
        this.jpaUserDaoImpl = jpaUserDaoImpl;
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


    @Autowired
    public void setEmailServiceImpl(EmailService emailServiceImpl) {
        this.emailServiceImpl = emailServiceImpl;
    }


    @Autowired
    public void setUserUpdatePasswordService(UserUpdatePasswordService userUpdatePasswordService) {
        this.userUpdatePasswordService = userUpdatePasswordService;
    }


    //treba dodati i za jezik (en, sr) u reqparam
    @PostMapping(value = "/registration")
    public ResponseEntity<String> registration(@RequestBody User user, @RequestParam ApplicationRoles role, @RequestParam UserLanguage language) {

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

        user.setRabat(user.getRabat());


        switch (language) {
            case EN:
                user.setLanguage(UserLanguage.EN.getLanguage());
                break;
            case SR:
                user.setLanguage(UserLanguage.SR.getLanguage());
                break;
        }

        jpaUserDaoImpl.add(user);

//        StringBuilder text = new StringBuilder();
//        text.append("Dear,\nThank you for registering with Green Land.\nPlease use the following credentials to log in and edit your personal information including your own password.\nUsername: ")
//                .append(user.getUsername())
//                .append("\nPassword: ").append(passwordEncoder.encode(user.getPassword())).append("\nThank you,\nGreen Land");
//
//        try {
//            emailServiceImpl.sendSimpleMessage(user.getUsername(), "Welcome to Green Land e shopping" , text.toString());
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

        return new ResponseEntity<String>("added User: ", HttpStatus.OK);
    }


    @PostMapping(value = "/login")
//    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        request.getSession();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.joining(""));

        return new ResponseEntity<String>(role, HttpStatus.OK);
    }


    @GetMapping(value = "/success/logout")
    public ResponseEntity<Object> logout() {

        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }


    @PostMapping(value = "/changePassword")
    public ResponseEntity<String> editUserAccount(@RequestBody User user, @RequestParam String newPassword) {
//        Optional<User> opt = jpaUserRepository.findByUsername(user.getUsername());
//        if(!opt.isPresent()) {
//            return new ResponseEntity<String>("can't find user with that username", HttpStatus.OK);
//        }
//        User userDb = opt.get();
        User userDb = jpaUserDaoImpl.findByUsername(user.getUsername());
        if (userDb == null) {
            return new ResponseEntity<String>("can't find user with that username", HttpStatus.OK);
        }


        if (user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().equals("") && passwordEncoder.matches(user.getPassword(), userDb.getPassword())) {
            userDb.setPassword(passwordEncoder.encode(newPassword));
            jpaUserDaoImpl.update(userDb);

            StringBuilder text = new StringBuilder();
            text.append("Dear,\n\nYou changed your password.\nPlease use the following credentials to log in and edit your personal information including your own password.\nUsername: ")
                    .append(user.getUsername())
                    .append("\nPassword: ").append(newPassword).append("\n\nThank you,\nGreen Land");

            try {
                emailServiceImpl.sendSimpleMessage(user.getUsername(), "Welcome to Green Land e shopping", text.toString());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<String>("successfully updated user password", HttpStatus.OK);
    }


//
//    @GetMapping(value = "/logout")
//    public ResponseEntity<String> logout() {
//
//        return new ResponseEntity<String>("Logout! ", HttpStatus.OK);
//    }


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

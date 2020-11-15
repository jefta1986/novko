package com.novko.api;


import com.novko.pdf.EmailService;
import com.novko.security.*;
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
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.stream.Collectors;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final UserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailServiceImpl;
    private final UserUpdatePasswordService userUpdatePasswordService;


    @Autowired
    public UserController(UserService userService, UserDetailsService myUserDetailsService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, EmailService emailServiceImpl, UserUpdatePasswordService userUpdatePasswordService) {
        this.userService = userService;
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.emailServiceImpl = emailServiceImpl;
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

        userService.save(user);

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


    //    @GetMapping(value = "/success/logout")
    @GetMapping(value = "/logout")
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
        User userDb = userService.findByUsername(user.getUsername());
        if (userDb == null) {
            return new ResponseEntity<String>("can't find user with that username", HttpStatus.OK);
        }


        if (user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().equals("") && passwordEncoder.matches(user.getPassword(), userDb.getPassword())) {
            userDb.setPassword(passwordEncoder.encode(newPassword));
            userService.save(userDb);

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

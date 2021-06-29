package com.novko.api;


import com.novko.api.exception.MailSendingException;
import com.novko.api.mapper.UserMapper;
import com.novko.api.request.*;
import com.novko.api.response.UserResponse;
import com.novko.pdf.EmailService;
import com.novko.security.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Validated
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final UserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UserUpdatePasswordService userUpdatePasswordService;


    @Autowired
    public UserController(UserService userService, UserDetailsService myUserDetailsService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, EmailService emailService, UserUpdatePasswordService userUpdatePasswordService) {
        this.userService = userService;
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.userUpdatePasswordService = userUpdatePasswordService;
    }


    @PostMapping(value = "/admin-register")
    @ApiOperation(value = "1.Register ADMIN")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public UserResponse registerAdmin(@Valid @RequestBody UserRequest userRequest, @NotNull @RequestParam UserLanguage language) {

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setActive(true);
        user.setRole(ApplicationRoles.ROLE_ADMIN.getRole());
        user.setRabat(userRequest.getRabat());
        user.setCode(userRequest.getCode());
        user.setFirma(userRequest.getFirma());
        user.setGrad(userRequest.getGrad());
        user.setUlica(userRequest.getUlica());
        user.setPib(userRequest.getPib());
        user.setMb(userRequest.getMb());


        switch (language) {
            case EN:
                user.setLanguage(UserLanguage.EN.getLanguage());
                break;
            case SR:
                user.setLanguage(UserLanguage.SR.getLanguage());
                break;
        }

        userService.save(user);
        return UserMapper.INSTANCE.toDto(user);
    }



    @PostMapping(value = "/registration")
    @ApiOperation(value = "Register New Account - USER or ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse registration(@Valid @RequestBody UserRequest userRequest, @NotNull @RequestParam ApplicationRoles role, @NotNull @RequestParam UserLanguage language) throws MailSendingException {

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setActive(true);

        switch (role) {
            case ROLE_USER:
                user.setRole(ApplicationRoles.ROLE_USER.getRole());
                user.setRabat(userRequest.getRabat());
                user.setCode(userRequest.getCode());

                //user info za pdf
                user.setFirma(userRequest.getFirma());
                user.setGrad(userRequest.getGrad());
                user.setUlica(userRequest.getUlica());
                user.setPib(userRequest.getPib());
                user.setMb(userRequest.getMb());
                break;
            case ROLE_ADMIN:
                user.setRole(ApplicationRoles.ROLE_ADMIN.getRole());
                break;
        }

        switch (language) {
            case EN:
                user.setLanguage(UserLanguage.EN.getLanguage());
                break;
            case SR:
                user.setLanguage(UserLanguage.SR.getLanguage());
                break;
        }

        userService.save(user);

        try {
            emailService.sendUserRegistrationEmail(language.name(), userRequest.getUsername(), userRequest.getPassword());
        } catch (MessagingException e) {
            throw new MailSendingException("Mail sending problem!");
//            e.printStackTrace();
        }

       return UserMapper.INSTANCE.toDto(user);
    }

    @PatchMapping(value = "/rest/user/edit")
    @ApiOperation(value = "Edit User Account - ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse editUser(@Valid @RequestBody EditUserRequest userRequest) throws MailSendingException {

        User user = userService.findById(userRequest.getId());

        if (user == null) {
            return null;
        }

        if (userRequest.getUsername() != null && !userRequest.getUsername().isEmpty() && !user.getUsername().equals(userRequest.getUsername())) {
            user.setUsername(userRequest.getUsername());
        }

        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty() && !user.getPassword().equals(userRequest.getPassword())) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }


        if (user.getRole().equals("ROLE_USER")) {
            if (userRequest.getRabat() != null || !userRequest.getRabat().equals(user.getRabat())) {
                user.setRabat(userRequest.getRabat());
            }

            if (userRequest.getCode() != null && !userRequest.getCode().isEmpty() && !userRequest.getCode().equals(user.getCode())) {
                user.setCode(userRequest.getCode());
            }

            //user info za pdf (sve informacije za usera)
            if (userRequest.getFirma() != null && !userRequest.getFirma().isEmpty() && !userRequest.getFirma().equals(user.getFirma())) {
                user.setFirma(userRequest.getFirma());
            }
            if (userRequest.getGrad() != null && !userRequest.getGrad().isEmpty() && !userRequest.getGrad().equals(user.getGrad())) {
                user.setGrad(userRequest.getGrad());
            }
            if (userRequest.getUlica() != null && !userRequest.getUlica().isEmpty() && !userRequest.getUlica().equals(user.getUlica())) {
                user.setUlica(userRequest.getUlica());
            }
            if (userRequest.getPib() != null && !userRequest.getPib().isEmpty() && !userRequest.getPib().equals(user.getPib())) {
                user.setPib(userRequest.getPib());
            }
            if (userRequest.getMb() != null && !userRequest.getMb().isEmpty() && !userRequest.getMb().equals(user.getMb())) {
                user.setMb(userRequest.getMb());
            }
        }

        if (userRequest.getLanguage() != null || !userRequest.getLanguage().isEmpty()) {
            if (userRequest.getLanguage().equals("EN") && !userRequest.getLanguage().equals(user.getLanguage())) {
                user.setLanguage(UserLanguage.EN.getLanguage());
            } else if (userRequest.getLanguage().equals("SR") && !userRequest.getLanguage().equals(user.getLanguage())) {
                user.setLanguage(UserLanguage.SR.getLanguage());
            }
        }

        userService.save(user);

        try {
            emailService.sendUserRegistrationEmail(user.getLanguage(), userRequest.getUsername(), userRequest.getPassword());
        } catch (MessagingException e) {
            throw new MailSendingException("Mail sending problem!");
        }

        return UserMapper.INSTANCE.toDto(user);
    }

    @DeleteMapping(value = "/rest/user/delete")
    @ApiOperation(value = "Delete User Account by Username (delete or deactivate)- ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUserAccount(@RequestParam String username) {

        userService.deleteByUsername(username);
        return new ResponseEntity<String>("User Account is deleted or deactivated!", HttpStatus.OK);
    }

    @PatchMapping(value = "/rest/user/active/{id}")
    @ApiOperation(value = "Set status (active or not) for User Account - ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse setActiveStatusForUser(@PathVariable("id") Long id) throws MailSendingException {

        User user = userService.findById(id);

        if (user == null) {
            return null;
        }

        if (user.isActive() == true) {
            user.setActive(false);
        }else {
            user.setActive(true);
        }

        userService.save(user);

        return UserMapper.INSTANCE.toDto(user);
    }


    // NOVI METOD koji salje objekat sa ostalim user podacima: username, language, marzu...
//    @PostMapping(value = "/registration")
//    public UserLoginResponse registration(@RequestBody User user, @RequestParam ApplicationRoles role, @RequestParam UserLanguage language) {
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setActive(true);
//
//        switch (role) {
//            case ROLE_USER:
//                user.setRole(ApplicationRoles.ROLE_USER.getRole());
//                break;
//            case ROLE_ADMIN:
//                user.setRole(ApplicationRoles.ROLE_ADMIN.getRole());
//                break;
//        }
//
//        user.setRabat(user.getRabat());
//
//
//        switch (language) {
//            case EN:
//                user.setLanguage(UserLanguage.EN.getLanguage());
//                break;
//            case SR:
//                user.setLanguage(UserLanguage.SR.getLanguage());
//                break;
//        }
//
//        userService.save(user);
//
////        StringBuilder text = new StringBuilder();
////        text.append("Dear,\nThank you for registering with Green Land.\nPlease use the following credentials to log in and edit your personal information including your own password.\nUsername: ")
////                .append(user.getUsername())
////                .append("\nPassword: ").append(passwordEncoder.encode(user.getPassword())).append("\nThank you,\nGreen Land");
////
////        try {
////            emailServiceImpl.sendSimpleMessage(user.getUsername(), "Welcome to Green Land e shopping" , text.toString());
////        } catch (MessagingException e) {
////            e.printStackTrace();
////        }
//
//        UserLoginResponse userLoginResponse = new UserLoginResponse();
//        userLoginResponse.setId(user.getId());
////        userLoginResponse.setCode(user.geCode());
//        userLoginResponse.setUsername(user.getUsername());
//        userLoginResponse.setLanguage(user.getLanguage());
//        userLoginResponse.setRabat(user.getRabat());
////        userLoginResponse.setActive(user.getActive());
//
//
//        return userLoginResponse;
//    }


    @PostMapping(value = "/login")
    @ApiOperation(value = "Login")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//    @GetMapping(value = "/login")
    public UserResponse login(@Email @NotBlank @RequestParam("username") String username, @NotBlank @RequestParam("password") String password) {

        User userFromDB = userService.findByUsername(username);
        if (userFromDB == null || userFromDB.isActive() == false) {
            return null;
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = SecurityUtils.getUserFromContext();

        if (user == null) {
            return null;
        }


//        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.joining(""));
//        SecurityContext sc = SecurityContextHolder.getContext();

//        String username = authentication.getName();
//        HttpSession session = request.getSession(true);
//        session.setAttribute("SPRING_SECURITY_CONTEXT", sc);


        return UserMapper.INSTANCE.toDto(user);
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "Logout")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = WebUtils.getCookie(request, "JSESSIONID");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (cookie != null && auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            SecurityContextHolder.clearContext();  //PROVERI!!!!!!
            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
    }

    @GetMapping(value = "/rest/users")
    @ApiOperation(value = "Get Users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        return UserMapper.INSTANCE.listToDto(userService.findAll());
    }

    @GetMapping(value = "/filtered")
    @ApiOperation(value = "Get All or Filtered Users")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserResponse> getAllOrFilteredUsers(@RequestParam(name = "active", required = false) Boolean active,
                                                    @RequestParam(name = "emailPart", required = false) String emailPart,
                                                    @RequestParam(name = "mbPart", required = false) String mbPart,
                                                    @RequestParam(name = "pibPart", required = false) String pibPart,
                                                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(name = "size", defaultValue = "12") Integer size,
                                                    @RequestParam(name = "sort", required = true) UserSortProperty sort,
                                                    @RequestParam(name = "direction", defaultValue = "ASC") SortDirection direction) {

        UserFilter userFilter = new UserFilter();
        if (active != null) {
            userFilter.setActive(active);
        }
        if (emailPart != null && !emailPart.isEmpty()) {
            userFilter.setEmailPart(emailPart);
        }
        if (mbPart != null && !mbPart.isEmpty()) {
            userFilter.setMbPart(mbPart);
        }
        if (pibPart != null && !pibPart.isEmpty()) {
            userFilter.setPibPart(pibPart);
        }

        Query query = new QueryBuilder()
                .setPage(page)
                .setSize(size)
                .setSortDirection(direction.name())
                .setSortProperty(sort.getField())
                .setFilter(userFilter)
                .createQuery();

        return UserMapper.INSTANCE.pageToDto(userService.findAllOrFiltered(query));
    }

    @GetMapping(value = "/rest/user/{id}")
    @ApiOperation(value = "Get User by Id - ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUserById(@PathVariable("id") Long id) {
        return UserMapper.INSTANCE.toDto(userService.findById(id));
    }

//    @GetMapping("/logoutsuccess")
//    @ApiOperation(value = "Logout Success Page")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//    public String logoutSuccessResponse() {
//       return "<h1>Logout Success Page</h1>";
//    }




//    @GetMapping(value="/logout")
//    public ExecutionStatus logout (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return new ExecutionStatu("USER_LOGOUT_SUCCESSFUL", "User is logged out");
//    }

    //    @GetMapping(value = "/success/logout")
//    @GetMapping(value = "/logout")
//    public ResponseEntity<Object> logout() {
//
//
//        return new ResponseEntity<Object>(null, HttpStatus.OK);
//    }



//    //proveri
//    @PostMapping(value = "/changePassword")
//    @ApiOperation(value = "Change USER Password")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> editUserAccount(@Valid @RequestBody User user, @NotBlank @RequestParam String newPassword) {
////        Optional<User> opt = jpaUserRepository.findByUsername(user.getUsername());
////        if(!opt.isPresent()) {
////            return new ResponseEntity<String>("can't find user with that username", HttpStatus.OK);
////        }
////        User userDb = opt.get();
//        User userDb = userService.findByUsername(user.getUsername());
//        if (userDb == null) {
//            return new ResponseEntity<String>("can't find user with that username", HttpStatus.OK);
//        }
//
//
//        if (user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().equals("") && passwordEncoder.matches(user.getPassword(), userDb.getPassword())) {
//            userDb.setPassword(passwordEncoder.encode(newPassword));
//            userService.save(userDb);
//
//            StringBuilder text = new StringBuilder();
//            text.append("Dear,\n\nYou changed your password.\nPlease use the following credentials to log in and edit your personal information including your own password.\nUsername: ")
//                    .append(user.getUsername())
//                    .append("\nPassword: ").append(newPassword).append("\n\nThank you,\nGreen Land");
//
//            try {
//                emailService.sendSimpleMessage(user.getUsername(), "Welcome to Green Land e shopping", text.toString());
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return new ResponseEntity<String>("successfully updated user password", HttpStatus.OK);
//    }


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

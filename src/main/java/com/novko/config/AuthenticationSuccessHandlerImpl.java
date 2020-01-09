//package com.novko.config;
//
//import com.novko.security.JpaUserRepository;
//import com.novko.security.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.security.Principal;
//
//
//@Component
//public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
//
//    //            private static final Logge logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);
//
////    @Autowired
////    HttpSession httpSession;
//
//    private JpaUserRepository jpaUserRepository;
//
//
//    @Autowired
//    public void setJpaUserRepository(JpaUserRepository jpaUserRepository) {
//        this.jpaUserRepository = jpaUserRepository;
//    }
//
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        String userName = "";
//
//        if (authentication.getPrincipal() instanceof Principal) {
//            userName = ((Principal) authentication.getPrincipal()).getName();
//
//        } else {
//            userName = ((User) authentication.getPrincipal()).getUsername();
//        }
//        HttpSession httpSession = httpServletRequest.getSession();
//        httpSession.setAttribute("userId", userName);
//    }
//}

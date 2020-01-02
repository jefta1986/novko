package com.novko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService myUserDetailsService;
//    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public void setMyUserDetailsService(UserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

//    @Autowired
//    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
//        this.authenticationSuccessHandler = authenticationSuccessHandler;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/", "/home", "/registration", "/login").permitAll()

                .antMatchers("/admin/**").hasRole("ADMIN")

                .antMatchers("/user/**").hasRole("USER")

                .anyRequest().authenticated()

//                .formLogin()
//                .loginPage("/login")
//                .permitAll().successHandler(authenticationSuccessHandler) // bitno!!!!
//                .and()
//                .logout()
//                .permitAll();


//  Pa napravis klasu:!!!!!
//        @Component
//        public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{
//
//            @Autowired HttpSession session; //autowiring session
//
//            @Autowired UserRepository repository; //autowire the user repo
//
//
//            private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                                Authentication authentication) throws IOException, ServletException {
//                // TODO Auto-generated method stub
//                String userName = "";
//                if(authentication.getPrincipal() instanceof Principal) {
//                    userName = ((Principal)authentication.getPrincipal()).getName();
//
//                }else {
//                    userName = ((User)authentication.getPrincipal()).getUsername();
//                }
//                logger.info("userName: " + userName);
//                //HttpSession session = request.getSession();
//                session.setAttribute("userId", userName);
//
//            }
//
//        }



//                .loginPage("/login.html")
//            .loginProcessingUrl("/login")
//            .failureUrl("/login.html?error=true")
//             
            .and()
            .logout().deleteCookies("JSESSIONID")
//             
//            .and()
//            .rememberMe().key("uniqueAndSecret")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




}

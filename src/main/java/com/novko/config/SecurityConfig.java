package com.novko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService myUserDetailsService;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public void setMyUserDetailsService(UserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Autowired
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/registration", "/login").permitAll()

                .antMatchers("/admin/**").hasRole("ADMIN")

                .antMatchers("/user/**").hasRole("USER")

                .anyRequest().authenticated()

                .and()
                .cors().disable()
                .csrf().disable()


                .httpBasic()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .usernameParameter("username").passwordParameter("password")
//                .defaultSuccessUrl("/home", true)
//                .loginProcessingUrl("/login")
//                .failureUrl("/login.html?error=true").permitAll()
//                .successHandler(authenticationSuccessHandler)
                .and()
                .logout().logoutUrl("/logout").permitAll().deleteCookies("JSESSIONID")
                .and()
                .rememberMe().key("rememberMe");
    }

        @Bean
        public PasswordEncoder passwordEncoder () {
            return new BCryptPasswordEncoder();
        }


//@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
        @Bean("authenticationManager")
        @Override
        public AuthenticationManager authenticationManagerBean () throws Exception {
            return super.authenticationManagerBean();
        }


    }


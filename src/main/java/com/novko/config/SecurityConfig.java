package com.novko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

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
                .antMatchers("/home").permitAll()
                
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                
                .antMatchers("/login").permitAll()
                
                .antMatchers("/logout").hasAnyRole("ADMIN","USER")
                
                .antMatchers("/admin/**").hasRole("ADMIN")
                
                .antMatchers("/registration").hasRole("ADMIN")

                .antMatchers("/user/**").hasRole("USER")

                .anyRequest().authenticated()

                .and()

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
                .rememberMe().key("rememberMe").and().csrf().disable().headers()
            // the headers you want here. This solved all my CORS problems! 
            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "http://localhost:4200"))
            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE"))
            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));
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
        
      /*  @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedMethods(Arrays.asList("*"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            configuration.setExposedHeaders(Arrays.asList("*"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }*/


    }


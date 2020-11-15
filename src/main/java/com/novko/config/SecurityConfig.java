//package com.novko.config;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    @Qualifier("myUserDetailsService")
//    private UserDetailsService myUserDetailsService;
//
////    @Autowired
////    private AuthenticationSuccessHandler authenticationSuccessHandler;
//
//    @Autowired @Qualifier("myUserDetailsService")
//    public void setMyUserDetailsService(UserDetailsService myUserDetailsService) {
//        this.myUserDetailsService = myUserDetailsService;
//    }
//
////    @Autowired
////    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
////        this.authenticationSuccessHandler = authenticationSuccessHandler;
////    }
//
////defaultna springova konfiguracija
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
////    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(authenticationProvider());
////    }
//
////    @Bean
////    public DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setUserDetailsService(myUserDetailsService);
////        authenticationProvider.setPasswordEncoder(passwordEncoder());
////        return authenticationProvider;
////    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                //.antMatchers("/", "/registration", "/rest/products/images").anonymous()
//                .antMatchers("/", "/registration", "/rest/products/images").permitAll()
////                .antMatchers("/**").authenticated()
//                .anyRequest().authenticated()
//                .and()
////                .httpBasic()
//                .formLogin()
//                .loginProcessingUrl("/login")
////                .loginPage("/login").permitAll()
////                .usernameParameter("username").passwordParameter("password")
////                .defaultSuccessUrl("/home", true)
////                .failureUrl("/login.html?error=true").permitAll()
////                .successHandler(authenticationSuccessHandler)
//                .and()
//                .logout()
//                .logoutUrl("/logout").permitAll()
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .and()
//                .rememberMe().key("rememberMe");
//        //.headers()
//        // the headers you want here. This solved all my CORS problems!
////            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "http://localhost:4200"))
////            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE"))
////            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
////            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
////            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));
//
//    }
//
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().antMatchers("/resources/static/**");
////    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//
//    //@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
////    @Bean("authenticationManager")
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//
//
//    //Nikola je ovo ubacio >
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource() {
////        final CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
////        configuration.setAllowedMethods(Arrays.asList("HEAD",
////                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
////        // setAllowCredentials(true) is important, otherwise:
////        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
////        configuration.setAllowCredentials(true);
////        // setAllowedHeaders is important! Without it, OPTIONS preflight request
////        // will fail with 403 Invalid CORS request
////        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
////        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////        return source;
////    }
//
//
//}
//

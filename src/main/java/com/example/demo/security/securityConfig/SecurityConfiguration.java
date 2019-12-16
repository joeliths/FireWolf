package com.example.demo.security.securityConfig;


import com.example.demo.exceptions.GlobalSecurityFilterExceptionHandler;
import com.example.demo.security.JPAUserDetailsService;
import com.example.demo.security.authenticationEventHandlers.SuccessHandler;
import com.example.demo.security.filters.CheckJwtFilter;

import com.example.demo.security.filters.LoginFilter;
import com.example.demo.security.providers.UserNamePasswordProvider;
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
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserNamePasswordProvider userNamePasswordProvider;
    @Autowired
    SuccessHandler successHandler;
    @Autowired
    SuccessHandler handler;
    @Autowired
    CheckJwtFilter checkJwtFilter;
    @Autowired
    private GlobalSecurityFilterExceptionHandler globalSecurityFilterExceptionHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userNamePasswordProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling().authenticationEntryPoint(globalSecurityFilterExceptionHandler);

        http.authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(checkJwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(checkJwtFilter, ExceptionTranslationFilter.class)
                .addFilterBefore(loginFilter(), checkJwtFilter.getClass());


//        http.csrf().disable().authorizeRequests()
//                //.antMatchers("/login", "/logout").permitAll()
//                //.antMatchers("/registerUser").permitAll()
//                .anyRequest().permitAll();

//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(new AntPathRequestMatcher("/login", "POST"), authenticationManagerBean(), handler);

        return loginFilter;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new JPAUserDetailsService();
    }
}

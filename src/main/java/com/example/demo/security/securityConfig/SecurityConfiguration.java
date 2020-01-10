package com.example.demo.security.securityConfig;


import com.example.demo.exceptions.GlobalSecurityFilterExceptionHandler;
import com.example.demo.security.JPAUserDetailsService;
import com.example.demo.security.authenticationEventHandlers.SuccessHandler;
import com.example.demo.security.filters.CheckJwtFilter;

import com.example.demo.security.filters.LoginFilter;
import com.example.demo.security.filters.exceptions.JWTExceptionCatcher;
import com.example.demo.security.providers.UserNamePasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserNamePasswordProvider userNamePasswordProvider;
    @Autowired
    private SuccessHandler successHandler;
    @Autowired
    private CheckJwtFilter checkJwtFilter;
    @Autowired
    private GlobalSecurityFilterExceptionHandler globalSecurityFilterExceptionHandler;
    @Autowired
    private JWTExceptionCatcher jwtExceptionCatcher;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userNamePasswordProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class).addFilterAfter(loginFilter(), ExceptionTranslationFilter.class).
                addFilterBefore(checkJwtFilter, loginFilter().getClass())
                .addFilterBefore(jwtExceptionCatcher, checkJwtFilter.getClass());

        http.csrf().disable().formLogin().disable().httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/store", "/store/details/**").permitAll()
                .antMatchers("/vendor", "/user", "/logout").hasRole("USER")
                .antMatchers("/customer", "customer/**", "/pending-orders", "/pending-orders/**").hasRole("CUSTOMER")
                .antMatchers("/vendor/**", "/store/**").hasRole("VENDOR")
                .antMatchers(HttpMethod.GET, "/products/**").hasRole("VENDOR")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/products/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().logout().disable()
                .exceptionHandling().authenticationEntryPoint(globalSecurityFilterExceptionHandler);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(new AntPathRequestMatcher("/login", "POST"), authenticationManagerBean(), successHandler);
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

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}

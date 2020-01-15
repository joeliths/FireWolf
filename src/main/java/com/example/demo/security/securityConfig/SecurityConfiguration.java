package com.example.demo.security.securityConfig;

import com.example.demo.exceptions.GlobalSecurityFilterExceptionHandler;
import com.example.demo.security.JPAUserDetailsService;
import com.example.demo.security.authenticationEventHandlers.SuccessHandler;
import com.example.demo.security.filters.CheckJwtFilter;

import com.example.demo.security.filters.LoginFilter;
import com.example.demo.security.filters.exceptions.JWTExceptionCatcher;
//import com.example.demo.security.filters.oauth2.CustomOidcUserService;
import com.example.demo.security.providers.UserNamePasswordProvider;
import com.google.common.collect.ImmutableList;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


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
//    @Autowired
//    CustomOidcUserService customOidcUserService;

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
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
                .antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/store", "/store/details/**").permitAll()
                .antMatchers("/vendor", "/user", "/logout").hasRole("USER")
                .antMatchers("/customer", "customer/**", "/pending-orders", "/pending-orders/**").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.GET, "/products/**").hasRole("VENDOR")
                .antMatchers("/vendor/**", "/store/**").hasRole("VENDOR")
                .antMatchers(HttpMethod.GET, "/products/**").hasRole("VENDOR")
                .antMatchers(HttpMethod.POST, "/products").hasAnyRole("VENDOR","ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/products/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().logout().disable()
                .exceptionHandling().authenticationEntryPoint(globalSecurityFilterExceptionHandler)
                .and().cors();
//                .and().oauth2Login()
//                .permitAll()
//                .userInfoEndpoint().oidcUserService(customOidcUserService);
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


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("*"));
        configuration.setExposedHeaders(ImmutableList.of("Access-Control-Allow-Origin", "Access-Control-Allow-Methods", "Access-Control-Allow-Headers", "Access-Control-Max-Age", "Authorization",
                "Access-Control-Request-Headers", "Access-Control-Request-Method"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

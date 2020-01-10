package com.example.demo.security.filters;

import com.example.demo.security.JPAUserDetailsService;
import com.example.demo.security.JWT.JwtService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CheckJwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(a -> System.out.println(a.getAuthority()));
        String authHeader = httpServletRequest.getHeader("Authorization");
        if(null != authHeader && authHeader.startsWith("Bearer ")) {
            //TODO: Check Database if Jwt is in badJwt-table
            String jwtToken = authHeader.substring(7);
            String username = jwtService.getSubject(jwtToken);
            UserDetails userDetails = fetchUserDetails(username);
                if (userDetails.getUsername() != null || userDetails.getAuthorities() != null
                                                      || userDetails.getPassword() != null) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                            userDetails.getPassword(), userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }else {
                    //TODO: Add JWT to badJwt-table
                }


        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UserDetails fetchUserDetails(String username){
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return userDetails;
        }catch(UsernameNotFoundException e){
            throw e;
        }
    }
}

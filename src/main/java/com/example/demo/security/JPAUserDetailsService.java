package com.example.demo.security;

import com.example.demo.entities.User;
import com.example.demo.entities.UserRole;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(userName)
            .orElseThrow(() -> new BadCredentialsException("Authentication error. " +
                    "No user with username '" + userName + "' was found."));

        List<GrantedAuthority> authorities = userRolesToGrantedAuthorities(user.getRoles());
        JPAUserDetails jpaUserDetails = new JPAUserDetails(user.getUserName(),user.getPassword(), authorities);

        return jpaUserDetails;
    }

    public List<GrantedAuthority> userRolesToGrantedAuthorities(Collection<UserRole> roles){
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
    }
}

package com.example.adapter.security;

import com.example.adapter.serviceImpl.UserServiceImpl;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.spi.port.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository utilisateurRepository;
    private UserService utilisateurService;

    public CustomUserDetailsService(UserRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurService = new UserServiceImpl(utilisateurRepository, new BCryptPasswordEncoder());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userApp = utilisateurService.findByUsername(username);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        return new org.springframework.security.core.userdetails.User(userApp.getUsername(), userApp.getPassword(),authorities);
    }
}
package com.example.adapter.controller;

import com.example.adapter.dto.LoginResponseDTO;
import com.example.adapter.dto.RegisterDto;
import com.example.adapter.dto.UserRequestDto;
import com.example.adapter.security.JWTGenerator;
import com.example.adapter.serviceImpl.UserServiceImpl;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.spi.port.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator generator;

    public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JWTGenerator generator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userService = new UserServiceImpl(userRepository, new BCryptPasswordEncoder());
        this.passwordEncoder = passwordEncoder;
        this.generator = generator;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDto> register (@RequestBody UserRequestDto userDto){
        try {
            User userApp = userService.save(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()));
            return ResponseEntity.ok(RegisterDto.builder().id(userApp.getId()).message("User created").build());
        }catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserRequestDto loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(LoginResponseDTO.builder().token(generator.generateToken(authentication)).build());
        }catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
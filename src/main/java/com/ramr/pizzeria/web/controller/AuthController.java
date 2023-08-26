package com.ramr.pizzeria.web.controller;

import com.ramr.pizzeria.persistence.entiity.Customer;
import com.ramr.pizzeria.persistence.entiity.User;
import com.ramr.pizzeria.persistence.entiity.UserRole;
import com.ramr.pizzeria.persistence.repository.UserRepository;
import com.ramr.pizzeria.persistence.repository.UserRoleRepository;
import com.ramr.pizzeria.service.CustomerService;
import com.ramr.pizzeria.service.dto.LoginDto;
import com.ramr.pizzeria.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());

        String jwt = jwtUtil.create(loginDto.getUsername());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {

        UserRole userRole = new UserRole();
        userRole.setUsername(user.getUsername());
        userRole.setRole("ADMIN");
        userRole.setGrantedDate(LocalDateTime.now());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User userBBDD = userRepository.save(user);

        userRoleRepository.save(userRole);


        return new ResponseEntity<>(userBBDD, HttpStatus.OK);
    }
}

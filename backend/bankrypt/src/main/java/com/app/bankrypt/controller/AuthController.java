package com.app.bankrypt.controller;

import com.app.bankrypt.model.Users;
import com.app.bankrypt.repository.UserRepository;
import com.app.bankrypt.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwutil;

    @PostMapping("/signin")
    public String authenticateUser(@RequestBody Users user) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return jwutil.generateToken(userDetails.getUsername());
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody Users user) {
        if (userRepository.existsWithUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }
        Users newUser = new Users(
                null,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getContactNumber(),
                user.getUsername(),
                encoder.encode(user.getPassword()),
                user.getUserRole()
                );
        userRepository.save(newUser);
        return "User registered successfully!";
    }
}

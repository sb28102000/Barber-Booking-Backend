package com.barber.auth_service.controller;

import com.barber.auth_service.entity.User;
import com.barber.auth_service.repository.UserRepository;
import com.barber.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getPassword().equals(password)) {
            // GENERATE REAL TOKEN HERE
            String token = jwtUtil.generateToken(username);

            Map<String, String> response = new HashMap<>();
            response.put("accessToken", token); // Send the real token
            return response;
        } else {
            throw new RuntimeException("Invalid Password");
        }
    }
}

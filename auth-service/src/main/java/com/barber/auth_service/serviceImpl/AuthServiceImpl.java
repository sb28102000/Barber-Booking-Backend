package com.barber.auth_service.serviceImpl;

import com.barber.auth_service.entity.User;
import com.barber.auth_service.repository.UserRepository;
import com.barber.auth_service.service.AuthService;
import com.barber.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser(User user) {
        // Business Logic: Set default role if missing
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        // In real app: Encode password here
        return userRepository.save(user);
    }

    @Override
    public Map<String, String> loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getPassword().equals(password)) {
            String token = jwtUtil.generateToken(username);

            Map<String, String> response = new HashMap<>();
            response.put("accessToken", token);
            response.put("role", user.getRole()); // <--- ADD THIS LINE

            return response;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

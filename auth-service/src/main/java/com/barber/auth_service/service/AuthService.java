package com.barber.auth_service.service;

import com.barber.auth_service.entity.User;

import java.util.Map;

public interface AuthService {
    User registerUser(User user);
    Map<String, String> loginUser(String username, String password);
}

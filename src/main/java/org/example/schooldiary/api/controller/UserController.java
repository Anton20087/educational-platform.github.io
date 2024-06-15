package org.example.schooldiary.api.controller;

import org.example.schooldiary.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public UserController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/role/{email}")
    public String getUserRoleByEmail(@PathVariable String email) {
        try {
            String userRole = userDetailsService.getUserRole(email);
            return userRole; // Повертаємо роль користувача у форматі рядка
        } catch (UsernameNotFoundException e) {
            return "USER_NOT_FOUND"; // Обробка випадку, коли користувач не знайдений
        }
    }
}


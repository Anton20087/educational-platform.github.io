package org.example.schooldiary.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"))) {
                return "teachers_home";
            } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
                return "students_home";
            } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CHIEF_TEACHER"))) {
                return "chief_teachers_home";
            }
        }
        // default home page if no matching role is found (shouldn't happen with proper security config)
        return "default_home";
    }
}

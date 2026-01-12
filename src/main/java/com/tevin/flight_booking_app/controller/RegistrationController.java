package com.tevin.flight_booking_app.controller;

import com.tevin.flight_booking_app.service.UserAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles user registration.
 */
@Controller
public class RegistrationController {

    private final UserAuthService authService;

    public RegistrationController(UserAuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers a new user.
     */
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password) {

        authService.register(username, password);
        return "redirect:/login";
    }
}

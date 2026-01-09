package com.tevin.flight_booking_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for handling authentication-related page routing.
 *
 * <p>This controller provides endpoints for displaying the login and registration pages
 * in the flight booking application.</p>
 *
 * <p>Note: This controller does not process form submissions or handle authentication logic;
 * it only maps GET requests to the appropriate Thymeleaf templates.</p>
 * 
 * @author Tevin Davis
 */
@Controller
public class AuthController {

    /**
     * Handles GET requests to the login page.
     *
     * @return the name of the Thymeleaf template for the login page
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    /**
     * Handles GET requests to the registration page.
     *
     * @return the name of the Thymeleaf template for the registration page
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; 
    }
}

package com.tevin.flight_booking_app.controller;

import com.tevin.flight_booking_app.service.UserXmlService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controller handling login and registration.
 */
@Controller
public class AuthController {

    private final UserXmlService userService;

    public AuthController(UserXmlService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        if (userService.authenticate(username, password)) {
            session.setAttribute("loggedInUser", username);
            return "redirect:/search";
        }

        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {

        userService.registerUser(username, email, password);
        return "redirect:/login";
    }
}

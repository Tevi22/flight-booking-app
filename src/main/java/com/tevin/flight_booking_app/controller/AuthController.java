package com.tevin.flight_booking_app.controller;

import com.tevin.flight_booking_app.service.UserXmlService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "You have been logged out successfully.");

        return "redirect:/login";
    }

    /**
     * Processes user login.
     *
     * @param username           the username
     * @param password           the password
     * @param session            the HTTP session
     * @param redirectAttributes flash attributes for alerts
     * @return redirect to search page or login page
     */
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (userService.authenticate(username, password)) {
            session.setAttribute("loggedInUser", username);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Login successful! Welcome back, " + username + ".");

            return "redirect:/search";
        }

        redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Invalid username or password.");

        return "redirect:/login";
    }

    /**
     * Processes user logout.
     *
     * @param session the HTTP session
     * @return redirect to home page
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /**
     * Processes user registration.
     *
     * @param username           the username
     * @param email              the email
     * @param password           the password
     * @param redirectAttributes flash attributes for alerts
     * @return redirect to login page
     */
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {

        userService.registerUser(username, email, password);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Registration successful! Please log in.");

        return "redirect:/login";
    }
}

package com.tevin.flight_booking_app.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * CustomErrorController handles all application errors and routes users
 * to friendly Thymeleaf error pages instead of the default WhiteLabel page.
 *
 * <p>This controller ensures that Thymeleaf fragments (header, footer, CSS)
 * are properly rendered for error pages such as 404, 403, and 500.</p>
 *
 * <p>This is required because static error pages do NOT process Thymeleaf syntax.</p>
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Global error handler mapping.
     *
     * <p>This method intercepts all application errors routed through "/error"
     * and selects the correct Thymeleaf error page based on HTTP status code.</p>
     *
     * @param request the incoming HTTP request that caused the error
     * @param model   the model used to pass data to the view
     * @return the name of the Thymeleaf error template to display
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        // Extract the HTTP error status code from the request
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // 404 - Page Not Found
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorCode", "404");
                model.addAttribute("errorMessage", "Page Not Found");
                return "error/404";
            }

            // 403 - Access Denied
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorCode", "403");
                model.addAttribute("errorMessage", "Access Denied");
                return "error/403";
            }

            // 500 - Internal Server Error
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorCode", "500");
                model.addAttribute("errorMessage", "Internal Server Error");
                return "error/500";
            }
            System.out.println("Custom error controller triggered");
        }

        // Catch-all fallback page for any other unexpected errors
        model.addAttribute("errorCode", "Error");
        model.addAttribute("errorMessage", "Unexpected Error Occurred");
        return "error/default";
    }
}

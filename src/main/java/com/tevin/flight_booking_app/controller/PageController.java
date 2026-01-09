package com.tevin.flight_booking_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controller responsible for serving general informational pages.
 *
 * <p>This controller provides routing for static or non-functional pages that
 * do not involve user input or backend interaction, such as the About page.</p>
 * 
 * <p>It helps keep the application organized by separating content pages
 * from functional controllers like those handling search or authentication.</p>
 * 
 * @author Tevin Davis
 */
@Controller
public class PageController {

     /**
     * Handles GET requests for the About page.
     *
     * @return the name of the Thymeleaf template for the About page
     */
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    /**
     * Handles GET requests for the Home page.
     *
     * @return the name of the Thymeleaf template for the Home page
     */
    @GetMapping("/")
    public String homePage() {
        return "index";
    }
    
}

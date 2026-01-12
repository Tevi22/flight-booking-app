package com.tevin.flight_booking_app;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 * Disables Thymeleaf template processing in controller tests.
 */
@TestConfiguration
public class TestConfig {

    @Bean
    SpringTemplateEngine templateEngine() {
        return new SpringTemplateEngine(); // empty engine = no parsing
    }
}

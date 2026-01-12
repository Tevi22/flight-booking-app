package com.tevin.flight_booking_app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Smoke test that verifies the Spring ApplicationContext loads successfully.
 */
@SpringBootTest
@ActiveProfiles("test")
class FlightBookingAppApplicationTests {

	 /**
     * Ensures the Spring context loads without errors.
     */
    @Test
    void contextLoads() {
        // If the application context fails to start, this test will fail automatically.
    }

}

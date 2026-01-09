package com.tevin.flight_booking_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Flight Booking Spring Boot application.
 * <p>
 * This class bootstraps the application using Spring Boot’s auto-configuration
 * and component scanning. When executed, it launches the embedded web server
 * (such as Tomcat) and initializes the Spring application context.
 * </p>
 */
@SpringBootApplication
public class FlightBookingAppApplication {

	/**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command-line arguments (optional)
     */
	public static void main(String[] args) {
		SpringApplication.run(FlightBookingAppApplication.class, args);
	}

}

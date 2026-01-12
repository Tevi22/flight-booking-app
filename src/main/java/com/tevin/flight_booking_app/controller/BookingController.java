package com.tevin.flight_booking_app.controller;

import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.service.BookingService;
import com.tevin.flight_booking_app.service.FlightService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for booking flights.
 */
@Controller
public class BookingController {

    private final BookingService bookingService;
    private final FlightService flightService;

    public BookingController(BookingService bookingService, FlightService flightService) {
        this.bookingService = bookingService;
        this.flightService = flightService;
    }

    @PostMapping("/book")
    public String bookFlight(
            @RequestParam Long flightId,
            @RequestParam int passengers,
            @RequestParam int children,
            HttpSession session) {

        String username = (String) session.getAttribute("loggedInUser");
        if (username == null) {
            return "redirect:/login";
        }

        FlightEntity flight = flightService.getFlightById(flightId);

        bookingService.bookFlight(username, flight, passengers, children);
        return "redirect:/bookings";
    }
}

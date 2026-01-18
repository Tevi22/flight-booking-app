package com.tevin.flight_booking_app.controller;

import com.tevin.flight_booking_app.model.BookingEntity;
import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.service.BookingService;
import com.tevin.flight_booking_app.service.FlightService;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final FlightService flightService;

    public BookingController(BookingService bookingService, FlightService flightService) {
        this.bookingService = bookingService;
        this.flightService = flightService;
    }

    /**
     * Handles booking submission from results page.
     * Uses PRG (Post/Redirect/Get) pattern.
     */
    @PostMapping("/book")
    public String bookFlight(
            @RequestParam Long flightId,
            @RequestParam int passengers,
            @RequestParam int children,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        // Session-based auth check
        String username = (String) session.getAttribute("loggedInUser");
        if (username == null) {
            return "redirect:/login";
        }

        // Fetch flight
        FlightEntity flight = flightService.getFlightById(flightId);
        if (flight == null) {
            return "redirect:/search";
        }

        // Create booking
        BookingEntity booking = bookingService.bookFlight(
                username,
                flight,
                passengers,
                children);

        // Pass ONLY booking ID across redirect
        redirectAttributes.addAttribute("bookingId", booking.getId());

        return "redirect:/booking/confirmation";
    }

    /**
     * Booking confirmation page.
     * Reloads booking from DB using bookingId.
     */
    @GetMapping("/confirmation")
    public String bookingConfirmation(
            @RequestParam Long bookingId,
            HttpSession session,
            Model model) {

        String username = (String) session.getAttribute("loggedInUser");
        if (username == null) {
            return "redirect:/login";
        }

        BookingEntity booking = bookingService.getBookingById(bookingId);

        // Safety check: prevent users from viewing others' bookings
        if (booking == null || !booking.getUsername().equals(username)) {
            return "redirect:/";
        }

        model.addAttribute("booking", booking);
        return "booking-confirmation";
    }

    /**
     * View all bookings for logged-in user.
     */
    @GetMapping
    public String viewBookings(HttpSession session, Model model) {

        String username = (String) session.getAttribute("loggedInUser");
        if (username == null) {
            return "redirect:/login";
        }

        // Normalize username
        username = username.trim().toLowerCase();

        List<BookingEntity> bookings = bookingService.getBookingsByUsername(username);

        model.addAttribute("bookings", bookings);

        return "book";
    }

    /**
     * Cancel a booking.
     */
    @PostMapping("/cancel")
    public String cancelBooking(
            @RequestParam Long bookingId,
            HttpSession session) {

        String username = (String) session.getAttribute("loggedInUser");
        if (username == null) {
            return "redirect:/login";
        }

        bookingService.cancelBooking(bookingId, username);

        return "redirect:/book";
    }
}

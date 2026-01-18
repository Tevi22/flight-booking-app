package com.tevin.flight_booking_app.service;

import com.tevin.flight_booking_app.model.BookingEntity;
import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.repository.BookingRepository;
import com.tevin.flight_booking_app.repository.FlightRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Service responsible for booking flights.
 */
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;

    public BookingService(BookingRepository bookingRepository,
                          FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
    }

    /**
     * Creates and saves a booking.
     */
    @Transactional
    public BookingEntity bookFlight(
            String username,
            FlightEntity flight,
            int passengers,
            int children) {

        int totalSeats = passengers + children;
        if (totalSeats <= 0) {
            throw new RuntimeException("At least one passenger required");
        }  

        if (flight.getAvailableSeats() < totalSeats) {
            throw new RuntimeException("Not enough available seats");
        }

        // Deduct seats
        flight.setAvailableSeats(flight.getAvailableSeats() - totalSeats);

        // Update flight
        flightRepository.save(flight);

        // Create booking
        BookingEntity booking = new BookingEntity();
        booking.setUsername(username);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDate.now());
        booking.setPassengers(passengers);
        booking.setChildren(children);
        double totalPrice = flight.getPrice() * passengers;
        booking.setTotalPrice(totalPrice);

        // Save booking
        return bookingRepository.save(booking);
    }

    /**
     * Retrieve bookings for a specific user.
     */
    public List<BookingEntity> getBookingsByUsername(String username) {
        return bookingRepository.findByUsername(username);
    }

    /*
     * Retrieves a booking by its ID.
     */
    public BookingEntity getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    /**
     * Cancels a booking and restores seats to the flight.
     */
    @Transactional
    public void cancelBooking(Long bookingId, String username) {

        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Security check
        if (!booking.getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized cancellation attempt");
        }

        FlightEntity flight = booking.getFlight();

        // Restore seats
        int restoredSeats = booking.getPassengers() + booking.getChildren();
        flight.setAvailableSeats(
                flight.getAvailableSeats() + restoredSeats
        );

        flightRepository.save(flight);

        // Delete booking
        bookingRepository.delete(booking);
    }

}

package com.tevin.flight_booking_app.service;

import com.tevin.flight_booking_app.model.BookingEntity;
import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Service responsible for booking flights.
 */
@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Creates and saves a booking.
     */
    public BookingEntity bookFlight(
            String username,
            FlightEntity flight,
            int passengers,
            int children) {

        BookingEntity booking = new BookingEntity();
        booking.setUsername(username);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDate.now());
        booking.setPassengers(passengers);
        booking.setChildren(children);

        double totalPrice = flight.getPrice() * passengers;
        booking.setTotalPrice(totalPrice);

        return bookingRepository.save(booking);
    }
}

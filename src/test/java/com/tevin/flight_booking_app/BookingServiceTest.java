package com.tevin.flight_booking_app;

import com.tevin.flight_booking_app.model.BookingEntity;
import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.repository.BookingRepository;
import com.tevin.flight_booking_app.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JPA test for Booking persistence.
 */
@DataJpaTest
class BookingServiceTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void savesBookingCorrectly() {

        // ✅ First create and save a Flight (required foreign key)
        FlightEntity flight = new FlightEntity();
        flight.setFlightNumber("AA101");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDepartureDate(LocalDate.of(2026, 2, 1));
        flight.setPrice(320.50);
        flight.setAvailableSeats(50);

        FlightEntity savedFlight = flightRepository.save(flight);

        // ✅ Now create a valid Booking
        BookingEntity booking = new BookingEntity();
        booking.setUsername("testuser");
        booking.setFlight(savedFlight);
        booking.setPassengers(1);
        booking.setChildren(0);
        booking.setTotalPrice(320.50);
        booking.setBookingDate(LocalDate.now());

        BookingEntity savedBooking = bookingRepository.save(booking);

        // Assertions
        assertNotNull(savedBooking.getId());
        assertNotNull(savedBooking.getBookingDate());
        assertNotNull(savedBooking.getFlight());
    }
}
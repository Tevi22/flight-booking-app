package com.tevin.flight_booking_app;

import com.tevin.flight_booking_app.model.BookingEntity;
import com.tevin.flight_booking_app.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JPA repository test for BookingRepository.
 * 
 * This test verifies that booking entities can be persisted
 * correctly to the database.
 */
@DataJpaTest
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Verifies that a booking is saved successfully
     * and an ID is generated.
     */
    @Test
    void savesBookingCorrectly() {
        // Arrange
        BookingEntity booking = new BookingEntity();
        booking.setUsername("testuser");

        // Act
        BookingEntity saved = bookingRepository.save(booking);

        // Assert
        assertNotNull(saved.getId(), "Saved booking should have a generated ID");
    }
}
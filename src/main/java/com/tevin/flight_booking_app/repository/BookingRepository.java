package com.tevin.flight_booking_app.repository;

import com.tevin.flight_booking_app.model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for flight bookings.
 */
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    List<BookingEntity> findByUsername(String username);
}

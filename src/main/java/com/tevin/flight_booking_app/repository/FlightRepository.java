package com.tevin.flight_booking_app.repository;

import com.tevin.flight_booking_app.model.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for flight database operations.
 */
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    /**
     * Finds flights by route and departure date.
     *
     * @param origin origin airport code/city
     * @param destination destination airport code/city
     * @param departureDate travel date
     * @return matching flights
     */
    List<FlightEntity> findByOriginIgnoreCaseAndDestinationIgnoreCaseAndDepartureDate(
            String origin,
            String destination,
            LocalDate departureDate
    );
}

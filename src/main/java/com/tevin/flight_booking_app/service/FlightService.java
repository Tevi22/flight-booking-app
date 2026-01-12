package com.tevin.flight_booking_app.service;

import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

/**
 * Service layer for flight-related business logic.
 */
@Service
public class FlightService {

    private final FlightRepository flightRepository;

    /**
     * Constructor injection for repository.
     */
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Searches for outbound flights.
     *
     * @param origin origin city
     * @param destination destination city
     * @param departDate departure date
     * @return list of matching flights (empty if none found)
     */
    public List<FlightEntity> searchOutboundFlights(
            String origin,
            String destination,
            LocalDate departDate) {

        return flightRepository
                .findByOriginIgnoreCaseAndDestinationIgnoreCaseAndDepartureDate(
                        origin, destination, departDate);
    }

    /**
     * Retrieves a flight by its ID.
     *
     * @param flightId the flight ID
     * @return FlightEntity if found
     * @throws IllegalArgumentException if flight does not exist
     */
    public FlightEntity getFlightById(Long flightId) {
        Optional<FlightEntity> flight = flightRepository.findById(flightId);

        return flight.orElseThrow(() ->
                new IllegalArgumentException("Flight not found with ID: " + flightId));
    }
}
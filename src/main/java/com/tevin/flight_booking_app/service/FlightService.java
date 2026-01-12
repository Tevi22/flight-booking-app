package com.tevin.flight_booking_app.service;

import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.repository.FlightRepository;
import org.springframework.stereotype.Service;

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
}
package com.tevin.flight_booking_app.api;

import com.tevin.flight_booking_app.model.Flight;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller that simulates a web-accessible flight search API.
 *
 * <p>
 * This "mock" external service is used to fulfill Milestone 2 requirements
 * for distributed technology interaction. In a production environment, this
 * controller could be replaced with or delegated to a real third-party airline
 * API.
 * </p>
 *
 * <p>
 * This endpoint demonstrates full request/response flow in a distributed
 * application: the user initiates a search, the controller layer forwards that
 * request
 * to FlightService, and this service returns results to the UI through the
 * controller.
 * </p>
 */
@RestController
public class FlightApiController {

        /**
         * HTTP GET endpoint for performing flight searches based on common input
         * criteria.
         * Currently returns sample/dummy flight data for testing purposes.
         *
         * <p>
         * <strong>Future Expansion:</strong><br>
         * - Integrate real flight search providers (e.g. Aviationstack, Skyscanner
         * API)<br>
         * - Support filtering, pagination, or pricing logic<br>
         * - Store retrieved results in a local database for booking workflow
         * </p>
         *
         * @param from       Departure city
         * @param to         Destination city
         * @param departDate Outbound travel date
         * @param returnDate Return travel date
         * @param passengers Number of passengers included in the request
         * @return A JSON array of Flight objects representing available itineraries
         */
        @GetMapping("/api/flights")
        public ResponseEntity<?> getFlights(
                        @RequestParam("from") String from,
                        @RequestParam("to") String to,
                        @RequestParam("departDate") String departDate,
                        @RequestParam("returnDate") String returnDate,
                        @RequestParam("passengers") int passengers) {

                // Basic input validation
                if (from == null || from.trim().isEmpty() ||
                                to == null || to.trim().isEmpty() ||
                                departDate == null || departDate.trim().isEmpty() ||
                                returnDate == null || returnDate.trim().isEmpty() ||
                                passengers <= 0) {

                        return ResponseEntity
                                        .badRequest()
                                        .body("Invalid input: All fields must be filled out and passengers must be > 0.");
                }

                // Dummy flight results
                List<Flight> flights = new ArrayList<>();

                flights.add(new Flight(
                                "TevinAir",
                                "TA123",
                                from,
                                to,
                                departDate + " 08:30",
                                199.99));

                flights.add(new Flight(
                                "TevinAir",
                                "TA456",
                                from,
                                to,
                                departDate + " 14:15",
                                249.99));

                flights.add(new Flight(
                                "SkyJet",
                                "SJ789",
                                from,
                                to,
                                departDate + " 19:45",
                                179.99));

                return ResponseEntity.ok(flights);
        }
}

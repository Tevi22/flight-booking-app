package com.tevin.flight_booking_app.service;

import com.tevin.flight_booking_app.model.Flight;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service class responsible for calling the internal flight search web service
 * and providing flight data to the controller layer.
 *
 * <p>
 * This class serves as a communication bridge in the distributed application:
 * it makes an HTTP request to the local REST API endpoint (/api/flights) and
 * processes the response into a list of Flight model objects. This design
 * separates controller responsibilities from data retrieval, enabling future
 * maintainability and scalability.
 * </p>
 *
 * <p>
 * <strong>Milestone 2:</strong> Calls a mock internal API to simulate remote
 * flight lookup.<br>
 * <strong>Milestone 3:</strong> Can be extended to use authentication or pull
 * additional user-based data.<br>
 * <strong>Final Enhancement:</strong> This may later be replaced with a real
 * external flight service.
 * </p>
 */
@Service
public class FlightService {
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Builds a request to the internal web service using query parameters provided
     * by the user and returns a list of relevant flight options.
     *
     * @param from       Departure airport/city
     * @param to         Destination airport/city
     * @param departDate Selected outbound travel date
     * @param returnDate Selected return date
     * @param passengers Number of travelers included in the search
     * @return A list of Flight objects representing available flights
     */
    public List<Flight> searchFlights(
            String from,
            String to,
            String departDate,
            String returnDate,
            int passengers) {

        String baseUrl = "http://localhost:8080/api/flights"
                + "?from=" + from
                + "&to=" + to
                + "&departDate=" + departDate
                + "&returnDate=" + returnDate
                + "&passengers=" + passengers;

        try {
            Flight[] response = restTemplate.getForObject(baseUrl, Flight[].class);
            return response != null ? Arrays.asList(response) : List.of();

        } catch (Exception e) {
            // Log the error (in real app you'd use a logger)
            System.err.println("Error contacting flight API: " + e.getMessage());

            // Return empty list so UI doesn't crash
            return List.of();
        }
    }
}

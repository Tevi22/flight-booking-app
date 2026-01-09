package com.tevin.flight_booking_app;

import com.tevin.flight_booking_app.api.FlightApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Integration test for {@link FlightApiController}.
 * <p>
 * This test class verifies the behavior of the /api/flights endpoint,
 * ensuring that it returns a valid list of flights based on query parameters.
 * It uses Spring's {@link WebMvcTest} to load only the web layer and mock dependencies.
 */
@WebMvcTest(FlightApiController.class)
public class FlightApiControllerTest {

    /**
     * MockMvc provides the ability to simulate HTTP requests
     * and assert responses for Spring MVC controllers.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests that the GET /api/flights endpoint:
     * <ul>
     *   <li>Returns HTTP 200 OK status</li>
     *   <li>Returns a JSON array of flights</li>
     *   <li>Includes expected flight data such as airline, flight number, from, and to</li>
     * </ul>
     *
     * @throws Exception if the mock HTTP request fails
     */
    @Test
    void getFlights_returnsListOfFlights() throws Exception {
        mockMvc.perform(get("/api/flights")
                .param("from", "IND")
                .param("to", "JFK")
                .param("departDate", "2025-12-15")
                .param("returnDate", "2025-12-20")
                .param("passengers", "1"))
                .andExpect(status().isOk())
                // Assert that the response is a JSON array with at least 1 element
                .andExpect(jsonPath("$[0].airline").exists())
                .andExpect(jsonPath("$[0].flightNumber").exists())
                .andExpect(jsonPath("$[0].from").value("IND"))
                .andExpect(jsonPath("$[0].to").value("JFK"));
    }
}

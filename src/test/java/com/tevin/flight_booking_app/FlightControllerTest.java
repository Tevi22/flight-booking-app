package com.tevin.flight_booking_app;

import com.tevin.flight_booking_app.controller.FlightController;
import com.tevin.flight_booking_app.model.Flight;
import com.tevin.flight_booking_app.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for {@link FlightController} endpoints using Spring MVC test framework.
 * Ensures endpoints return correct views and model attributes.
 */
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

        @Autowired
        private MockMvc mockMvc;

        // Inject mock version of the FlightService
        @MockBean
        private FlightService flightService;

        /**
         * Test that the /search page loads with status 200 and includes 'searchRequest'
         * model.
         *
         * @throws Exception if the request fails
         */
        @Test
        void searchPageLoadsSuccessfully() throws Exception {
                mockMvc.perform(get("/search"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("search"))
                                .andExpect(model().attributeExists("searchRequest")); // Changed from 'message'
        }

        /**
         * Test the /search/results endpoint using query params and verify it returns
         * expected model and view.
         *
         * @throws Exception if the request fails
         */
        @Test
        void searchResults_usesFlightServiceAndReturnsResultsView() throws Exception {
                // Arrange: sample mock flights returned by the FlightService
                List<Flight> sampleFlights = List.of(
                                new Flight("TevinAir", "TA123", "IND", "JFK", "2025-12-15 08:30", 199.99),
                                new Flight("SkyJet", "SJ789", "IND", "JFK", "2025-12-15 14:15", 249.99));

                when(flightService.searchFlights("IND", "JFK", "2025-12-15", "2025-12-20", 1))
                                .thenReturn(sampleFlights);

                mockMvc.perform(get("/search/results")
                                .param("from", "IND")
                                .param("to", "JFK")
                                .param("departDate", "2025-12-15")
                                .param("returnDate", "2025-12-20")
                                .param("passengers", "1"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("results"))
                                .andExpect(model().attributeExists("searchRequest")) // changed
                                .andExpect(model().attributeExists("flights")); // kept
        }
}

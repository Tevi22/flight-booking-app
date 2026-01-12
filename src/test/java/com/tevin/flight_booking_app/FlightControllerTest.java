package com.tevin.flight_booking_app;

import com.tevin.flight_booking_app.controller.FlightController;
import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller tests for FlightController.
 */
@WebMvcTest(controllers = FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FlightService flightService;

    @Test
    void searchFlights_returnsResultsView_withFlightsInModel() throws Exception {
        // Arrange
        FlightEntity flight = new FlightEntity();
        flight.setFlightNumber("AA101");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDepartureDate(LocalDate.of(2026, 2, 1));
        flight.setPrice(320.50);
        flight.setAvailableSeats(50);

        List<FlightEntity> flights = List.of(flight);

        when(flightService.searchOutboundFlights(
                "JFK",
                "LAX",
                LocalDate.of(2026, 2, 1)
        )).thenReturn(flights);

        // Act + Assert
        mockMvc.perform(get("/search/results")
                        .param("from", "JFK")
                        .param("to", "LAX")
                        .param("tripType", "oneway")
                        .param("departDate", "2026-02-01")
                        .param("passengers", "1")
                        .param("children", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("results"))
                .andExpect(model().attributeExists("flights"))
                .andExpect(model().attribute("from", "JFK"))
                .andExpect(model().attribute("to", "LAX"))
                .andExpect(model().attribute("tripType", "oneway"))
                .andExpect(model().attribute("passengers", 1))
                .andExpect(model().attribute("children", 0));
    }
}

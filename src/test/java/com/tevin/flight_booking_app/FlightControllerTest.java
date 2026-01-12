package com.tevin.flight_booking_app;

import com.tevin.flight_booking_app.controller.FlightController;
import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller-only test (no Thymeleaf rendering)
 */
@WebMvcTest(controllers = FlightController.class)
@Import(TestConfig.class) // ⬅️ KEY FIX
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FlightService flightService;

    @Test
    void searchFlights_returnsResultsView_withFlightsInModel() throws Exception {

        FlightEntity flight = new FlightEntity();
        flight.setId(1L);
        flight.setFlightNumber("AA101");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDepartureDate(LocalDate.of(2026, 2, 1));
        flight.setPrice(320.50);
        flight.setAvailableSeats(50);

        when(flightService.searchOutboundFlights(
                "JFK", "LAX", LocalDate.of(2026, 2, 1)))
                .thenReturn(List.of(flight));

        mockMvc.perform(get("/search/results")
                        .param("from", "JFK")
                        .param("to", "LAX")
                        .param("tripType", "oneway")
                        .param("departDate", "2026-02-01")
                        .param("passengers", "1")
                        .param("children", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("results"))
                .andExpect(model().attributeExists("flights"));
    }
}

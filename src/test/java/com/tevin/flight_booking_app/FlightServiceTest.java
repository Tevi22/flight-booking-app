package com.tevin.flight_booking_app;

import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.repository.FlightRepository;
import com.tevin.flight_booking_app.service.FlightService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Unit tests for FlightService.
 */
@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    void getFlightById_returnsFlight_whenFound() {
        FlightEntity flight = new FlightEntity();
        flight.setId(1L);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        FlightEntity result = flightService.getFlightById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
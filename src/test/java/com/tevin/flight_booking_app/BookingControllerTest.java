package com.tevin.flight_booking_app;

import com.tevin.flight_booking_app.controller.BookingController;
import com.tevin.flight_booking_app.service.BookingService;
import com.tevin.flight_booking_app.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookingController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
class BookingControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private BookingService bookingService;

        @MockitoBean
        private FlightService flightService;

        @Test
        void showBookingsPage_shouldRenderBookPage() throws Exception {

                when(bookingService.getBookingsByUsername("testuser"))
                                .thenReturn(Collections.emptyList());

                mockMvc.perform(
                                get("/booking")
                                                .sessionAttr("loggedInUser", "testuser"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("book"))
                                .andExpect(model().attributeExists("bookings"));
        }

        @Test
        void cancelBooking_shouldRedirectToBookingPage() throws Exception {

                mockMvc.perform(
                                post("/booking/cancel")
                                                .param("bookingId", "1")
                                                .sessionAttr("loggedInUser", "testuser"))
                                .andExpect(status().is3xxRedirection())
                                .andExpect(redirectedUrl("/book"));
        }
}

package com.tevin.flight_booking_app;

import jakarta.servlet.RequestDispatcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.tevin.flight_booking_app.controller.CustomErrorController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Unit tests for {@link CustomErrorController}.
 *
 * <p>This test class uses {@code @WebMvcTest} to test the error-handling behavior
 * of the controller in isolation without needing to start the full application context.</p>
 */
@WebMvcTest(CustomErrorController.class)
public class CustomErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test case to verify that a 404 error status returns the correct Thymeleaf error view.
     */
    @Test
    public void shouldReturn404ErrorPage() throws Exception {
        mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 404))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"));
    }

    /**
     * Test case to verify that a 500 error status returns the correct Thymeleaf error view.
     */
    @Test
    public void shouldReturn500ErrorPage() throws Exception {
        mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 500))
                .andExpect(status().isOk())
                .andExpect(view().name("error/500"));
    }

    /**
     * Test case to verify that a 403 error status returns the correct Thymeleaf error view.
     */
    @Test
    public void shouldReturn403ErrorPage() throws Exception {
        mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 403))
                .andExpect(status().isOk())
                .andExpect(view().name("error/403"));
    }

    /**
     * Test case to verify that an unknown error status (e.g., 418) returns the default fallback error view.
     */
    @Test
    public void shouldReturnDefaultErrorPage() throws Exception {
        mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 418)) // Teapot Easter Egg 😄
                .andExpect(status().isOk())
                .andExpect(view().name("error/default"));
    }
}

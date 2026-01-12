package com.tevin.flight_booking_app.controller;

import com.tevin.flight_booking_app.model.FlightEntity;
import com.tevin.flight_booking_app.model.SearchRequest;
import com.tevin.flight_booking_app.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Controller responsible for handling flight search requests.
 */
@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Displays the flight search page.
     */
    @GetMapping("/search")
    public String showSearchPage(Model model) {
        model.addAttribute("searchRequest", new SearchRequest());
        return "search";
    }

    /**
     * Handles flight search results.
     */
    @GetMapping("/search/results")
    public String showResults(
            @ModelAttribute("searchRequest") SearchRequest searchRequest,
            Model model) {

        List<FlightEntity> flights =
                flightService.searchOutboundFlights(
                        searchRequest.getFrom(),
                        searchRequest.getTo(),
                        searchRequest.getDepartDate()
                );

        model.addAttribute("flights", flights);

        if (flights.isEmpty()) {
            model.addAttribute(
                    "noResultsMessage",
                    buildNoResultsMessage(searchRequest)
            );
        }

        return "results";
    }

    /**
     * Builds a friendly "no results" message.
     */
    private String buildNoResultsMessage(SearchRequest request) {

        boolean isRoundTrip = "round".equalsIgnoreCase(request.getTripType());
        boolean hasReturnDate = request.getReturnDate() != null;

        String dateText = (isRoundTrip && hasReturnDate)
                ? request.getDepartDate() + " to " + request.getReturnDate()
                : request.getDepartDate().toString();

        return "No flights found from "
                + request.getFrom()
                + " to "
                + request.getTo()
                + " for "
                + dateText
                + ".";
    }
}
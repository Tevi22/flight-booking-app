package com.tevin.flight_booking_app.controller;

import com.tevin.flight_booking_app.model.Flight;
import com.tevin.flight_booking_app.model.SearchRequest;
import com.tevin.flight_booking_app.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller responsible for handling flight search requests and displaying
 * search results.
 *
 * <p>
 * This controller provides the endpoints to render the search form and to
 * process flight
 * search results using the {@link FlightService}.
 * </p>
 *
 * @author Tevin
 * @version 1.0
 */
@Controller
public class FlightController {

    private final FlightService flightService;

    /**
     * Constructor-based dependency injection of the flight service.
     *
     * @param flightService the service layer responsible for retrieving flight data
     */

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Handles GET requests to the /search endpoint and loads the search form.
     *
     * @param model Spring's model object used to pass attributes to the Thymeleaf
     *              template
     * @return the name of the Thymeleaf template for the search page
     */
    @GetMapping("/search")
    public String showSearchForm(Model model) {
        // Initialize an empty SearchRequest object to bind form data
        SearchRequest searchRequest = new SearchRequest();

        // Set default values if needed
        searchRequest.setTripType("round"); // Default to round trip

        // Message can be customized or removed later; acts as a placeholder
        model.addAttribute("searchRequest", searchRequest);
        return "search";
    }

    /**
     * Handles GET requests to the /search/results endpoint.
     * Accepts search parameters, calls the flight service, and renders the results
     * view.
     *
     * @param from       the origin airport code (e.g., "IND")
     * @param to         the destination airport code (e.g., "JFK")
     * @param tripType   the type of trip ("oneway" or "round")
     * @param departDate the departure date (format: YYYY-MM-DD)
     * @param returnDate the return date (format: YYYY-MM-DD)
     * @param passengers the number of passengers
     * @param children   the number of children
     * @param model      Spring's model object used to pass attributes to the view
     * @return the name of the Thymeleaf template for the results page
     */
    @GetMapping("/search/results")
    public String searchFlights(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("tripType") String tripType,
            @RequestParam("departDate") String departDate,
            @RequestParam("returnDate") String returnDate,
            @RequestParam("passengers") int passengers,
            @RequestParam("children") int children,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            Model model) {

        if ("oneway".equalsIgnoreCase(tripType)) {
            // Ignore return date for one-way
            returnDate = null;
        }

        // Retrieve list of matching flights using the service layer
        List<Flight> flights = flightService.searchFlights(from, to, departDate, returnDate, passengers);

        // Add user input and result list to the model to render in the view
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setFrom(from);
        searchRequest.setTo(to);
        searchRequest.setDepartDate(departDate);
        searchRequest.setReturnDate(returnDate);
        searchRequest.setPassengers(passengers);
        searchRequest.setChildren(children);
        searchRequest.setTripType(tripType);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("flights", flights);

        return "results";
    }
}
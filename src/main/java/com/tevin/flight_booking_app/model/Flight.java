package com.tevin.flight_booking_app.model;

/**
 * Represents a single flight option returned from the flight web service.
 * This model holds relevant flight details used for displaying search results
 * and transferring data between backend and frontend.
 */
public class Flight {

    /** Name of the airline operating the flight (e.g., Delta, United). */
    private String airline;

    /** Unique flight number identifier (e.g., DL2345). */
    private String flightNumber;

    /** Departure airport code (e.g., IND). */
    private String from;

    /** Arrival airport code (e.g., JFK). */
    private String to;

    /** Scheduled departure time (e.g., 2025-12-15 08:30). */
    private String departureTime;

    /** Price of the flight (e.g., $199.99). */
    private Double price;

    /**
     * Default no-argument constructor.
     * Required for JSON deserialization and frameworks like Jackson.
     */
    public Flight() {
    }

    /**
     * Constructs a Flight object with all fields.
     *
     * @param airline       The airline name
     * @param flightNumber  The flight number
     * @param from          Departure airport code
     * @param to            Arrival airport code
     * @param departureTime Departure time in ISO string format
     * @param price         Ticket price
     */
    public Flight(String airline, String flightNumber, String from, String to,
            String departureTime, Double price) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.price = price;
    }

    // Getters and setters

    /** @return the airline name */
    public String getAirline() {
        return airline;
    }

    /** @param airline the airline name to set */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /** @return the flight number */
    public String getFlightNumber() {
        return flightNumber;
    }

    /** @param flightNumber the flight number to set */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /** @return the departure airport code */
    public String getFrom() {
        return from;
    }

    /** @param from the departure airport code to set */
    public void setFrom(String from) {
        this.from = from;
    }

    /** @return the arrival airport code */
    public String getTo() {
        return to;
    }

    /** @param to the arrival airport code to set */
    public void setTo(String to) {
        this.to = to;
    }

    /** @return the departure time */
    public String getDepartureTime() {
        return departureTime;
    }

    /** @param departureTime the departure time to set */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /** @return the price of the flight */
    public Double getPrice() {
        return price;
    }

    /** @param price the price of the flight to set */
    public void setPrice(Double price) {
        this.price = price;
    }

}

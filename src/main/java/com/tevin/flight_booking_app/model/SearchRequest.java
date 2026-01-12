package com.tevin.flight_booking_app.model;

import java.time.LocalDate;

/**
 * DTO for binding flight search form data.
 */
public class SearchRequest {

    private String from;
    private String to;
    private String tripType;
    private LocalDate departDate;
    private LocalDate returnDate;
    private int passengers;
    private int children;

    // Getters and setters
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getTripType() { return tripType; }
    public void setTripType(String tripType) { this.tripType = tripType; }

    public LocalDate getDepartDate() { return departDate; }
    public void setDepartDate(LocalDate departDate) { this.departDate = departDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public int getPassengers() { return passengers; }
    public void setPassengers(int passengers) { this.passengers = passengers; }

    public int getChildren() { return children; }
    public void setChildren(int children) { this.children = children; }
}
package com.tevin.flight_booking_app.model;

/**
 * Data Transfer Object (DTO) that holds user input from the flight search form.
 *
 * <p>
 * This object is automatically populated using Spring MVC's
 * 
 * @ModelAttribute and is passed to the controller for processing.
 *                 </p>
 */
public class SearchRequest {
    private String from;
    private String to;
    private String departDate;
    private String returnDate;
    private int passengers;
    private int children;
    private String tripType;
    private String sortBy;

    // Default constructor (required for Spring's data binding)
    public SearchRequest() {
    }

    // Getters and Setters

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
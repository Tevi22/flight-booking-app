# TevinAir – Flight Booking Web Application (Milestone Two)

## Overview

**TevinAir** is a fictional air travel booking web application developed by **Tevin Davis** as part of **Milestone Two** for the IT 634: Distributed Application Development course at **Southern New Hampshire University**.

The application simulates a real-world flight search platform. Users can search for flights based on city, date, and number of passengers. The project showcases a RESTful architecture, modular MVC design, Thymeleaf integration, and unit test coverage.

---

## Milestone Two Functionality

### RESTful Search Functionality

- Custom **FlightService** simulates flight results via a backend service layer.
- Endpoint: `/search/results` accepts search form input and returns mock data via REST-style MVC logic.

### UI + MVC Integration

- Form-based input through Thymeleaf + Spring Boot.
- Bootstrap-based, responsive search page and results view.
- Controller: `FlightController` handles routing and service interaction.

### Object-Oriented Design

- Modular Java classes for `Flight`, `SearchRequest`, and `FlightService`.
- MVC separation of concerns across controller, model, and view layers.

### Unit Testing

- `FlightControllerTest` validates expected model attributes and routing via `MockMvc`.
- Service logic tested in isolation using `FlightServiceTest`.

### Error Handling

- Custom error controller (`CustomErrorController`) returns:
  - 404 (Not Found)
  - 403 (Forbidden)
  - 500 (Server Error)
- Error templates rendered using shared Thymeleaf fragments.

---

## Tech Stack

| Layer         | Technology                  |
|---------------|-----------------------------|
| Language      | Java 17                     |
| Framework     | Spring Boot 3 (MVC)         |
| Template      | Thymeleaf                   |
| UI Framework  | Bootstrap 5 (CDN)           |
| Testing       | JUnit 5, MockMvc            |
| Build Tool    | Maven                       |
| IDE           | VSCode                      |

---

## Project Structure

src/
├── main/
│ ├── java/com/tevin/flight_booking_app/
│ │ ├── controller/ # Controllers (FlightController, ErrorController)
│ │ ├── model/ # POJOs (Flight, SearchRequest)
│ │ └── service/ # Business logic (FlightService)
│ └── resources/
│ ├── static/css/ # Custom styles
│ └── templates/
│ ├── fragments/ # Reusable layout components
│ ├── error/ # 403, 404, 500 error templates
│ ├── search.html # Search page
│ └── results.html # Results display
│ ├── index.html # Root page
│ └── about.html # About page
│ ├── login.html # Login page
│ └── register.html # Registration page
└── test/
└── com.tevin.flight_booking_app/
├── FlightControllerTest.java
└── FlightServiceTest.java
---

## Running the Application

### Start the App

-Run the following in terminal to launch site:

```bash
./mvnw spring-boot:run
```

### Home/Search Page

-Visit: <http://localhost:8080>
-Click Search Flight Button origin, destination, dates, and passenger count to search for flights.

### Sample Result URL

-Visit: <http://localhost:8080/search/results?from=NYC&to=LAX&departDate=2025-12-25&returnDate=2025-12-30&passengers=2>

### Error Page Demo

-Visit <http://localhost:8080/notfound> → Triggers 404
-Visit <http://localhost:8080/forbidden> or force error in controller for 403/500 test

## Running Unit Tests

-Run the following in terminal to execute controller and service unit tests:

```bash
./mvnw test
```

### Expected output

-All tests pass (FlightControllerTest, FlightServiceTest)
-Coverage includes search routing, model attributes, and view rendering.

Author
Tevin Davis
Master of Science – Information Technology
Southern New Hampshire University
Milestone Two – Completed December 2025

### Notes

-RESTful API simulated using in-memory service (no external API used).
-Form validation ensures no blank or malformed values.
-Fully responsive layout tested in Chrome and Edge.
-Error handling verified using browser and test cases.
-Designed for future enhancement with user login and external API integration.

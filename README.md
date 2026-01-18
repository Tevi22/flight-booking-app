# TevinAir – Flight Booking Web Application

TevinAir is a full-stack flight booking web application developed as the **Final Project for IT 634: Distributed Application Development** at **Southern New Hampshire University (SNHU)**.  
The application demonstrates modern enterprise development practices using **Spring Boot**, **RESTful services**, **MVC architecture**, and **MySQL database integration**.

---

## Project Overview

TevinAir allows users to:

- Search for flights by origin, destination, and departure date
- View available flights and seat availability
- Register and log in using session-based authentication
- Book flights and receive booking confirmations
- View previously booked flights
- Cancel bookings and restore seat availability

This project showcases backend API design, server-side rendering, persistence, transaction handling, and clean separation of concerns.

---

## System Architecture Overview

TevinAir follows a layered architecture combining **Model-View-Controller (MVC)** with **RESTful service principles** and persistent database storage.

### Presentation Layer (View)

The frontend is built using **Thymeleaf**, **HTML5**, and **Bootstrap 5**. Pages such as flight search, results, booking confirmation, and booking history are rendered server-side using model attributes passed from controllers.

### Controller Layer (MVC + REST)

Spring MVC controllers handle incoming HTTP requests, validate input, manage session state, and route requests to the service layer. REST-style endpoints are used for searching flights, booking flights, and canceling bookings using proper HTTP methods (`GET`, `POST`).

### Service Layer

The service layer contains the core business logic, including:

- Flight availability checks
- Booking creation
- Price calculation
- Booking cancellation
- Seat restoration upon cancellation

Transactional boundaries ensure database consistency.

### Data Layer (Database)

Persistent storage is implemented using **MySQL** and **Spring Data JPA**. Entity classes represent flights and bookings, while repository interfaces abstract database access. Transactions ensure seat counts remain accurate when bookings are created or canceled.

This architecture promotes scalability, maintainability, and alignment with real-world enterprise systems.

---

## Project Structure

```text
flight-booking-app/
│
├── .mvn/
├── .vscode/
├── data/
│   └── users.xml
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/tevin/flight_booking_app/
│   │   │       ├── api/
│   │   │       │   └── FlightApiController.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── BookingController.java
│   │   │       │   ├── CustomErrorController.java
│   │   │       │   ├── FlightController.java
│   │   │       │   └── PageController.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   ├── BookingEntity.java
│   │   │       │   ├── Flight.java
│   │   │       │   ├── FlightEntity.java
│   │   │       │   └── SearchRequest.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── BookingRepository.java
│   │   │       │   └── FlightRepository.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── BookingService.java
│   │   │       │   ├── FlightService.java
│   │   │       │   └── UserXmlService.java
│   │   │       │
│   │   │       └── FlightBookingAppApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   ├── images/
│   │       │   └── js/
│   │       │
│   │       ├── templates/
│   │       │   ├── fragments/
│   │       │   │   ├── alert.html
│   │       │   │   ├── footer.html
│   │       │   │   ├── head.html
│   │       │   │   ├── header.html
│   │       │   │   └── scripts.html
│   │       │   │
│   │       │   ├── error/
│   │       │   ├── index.html
│   │       │   ├── search.html
│   │       │   ├── results.html
│   │       │   ├── booking-confirmation.html
│   │       │   ├── book.html
│   │       │   ├── login.html
│   │       │   ├── register.html
│   │       │   └── about.html
│   │       │
│   │       ├── application.properties
│   │       ├── application-test.properties
│   │       ├── schema.sql
│   │       └── data.sql
│   │
│   └── test/
│       └── java/com/tevin/flight_booking_app/
│           ├── BookingServiceTest.java
│           ├── CustomErrorControllerTest.java
│           ├── FlightApiControllerTest.java
│           ├── FlightControllerTest.java
│           ├── FlightServiceTest.java
│           └── TestConfig.java
│
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Technology Stack

| Layer | Technology |
|-----|-----------|
| Backend | Java 17, Spring Boot 3 |
| Frontend | Thymeleaf, HTML5, Bootstrap 5 |
| API Style | RESTful endpoints |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Build Tool | Maven |
| Testing | JUnit, Spring Boot Test |

---

## Key Features

- RESTful flight search endpoint
- Case-insensitive search handling
- Session-based user authentication
- Flight booking with seat tracking
- Booking confirmation page
- Booking history per user
- Booking cancellation with seat restoration
- MVC-based UI rendering
- MySQL persistence with transactional integrity
- Modular, well-documented codebase

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

### Use the Application

1. Navigate to the home page
2. Search for flights
3. Log in/Register to enable booking
4. Book a flight
5. View booking confirmation
6. Manage bookings from the booking page
7. Cancel bookings and restore seats

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

## Testing

Unit tests are implemented for controllers and services to validate:

- REST endpoint behavior
- Search logic
- Booking creation and cancellation
- Error handling and edge cases

Testing ensures reliability and correctness of the distributed components.

---

## Database Design

The application uses two primary entities:

### Flights

- Flight number
- Origin city
- Destination city
- Departure date
- Available seats
- Price

### Bookings

- Username
- Flight reference
- Booking date
- Passenger count
- Children count
- Total price

Seat availability is dynamically updated when bookings are created or canceled.

---

## Future Improvements

While TevinAir meets all functional requirements for the final project, several enhancements could be implemented in a production-ready version of the application. Future improvements may include integration with a real-world flight data provider (such as SkyPicker or Amadeus APIs), support for round-trip and multi-city bookings, and role-based authentication using Spring Security. Additional enhancements could also include seat selection, fare classes, email booking confirmations, pagination for large datasets, and performance optimization through caching.

---

## Source Code Repository

GitHub Repository:  
<https://github.com/Tevi22/flight-booking-app>

---

## Developer

**Tevin Davis**  
Master of Science in Information Technology  
Southern New Hampshire University  

This project was developed as part of the IT 634 Distributed Application Development course and is intended for academic and portfolio demonstration purposes.

---

## License

This project is for educational use only.

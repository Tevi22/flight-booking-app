CREATE TABLE IF NOT EXISTS flights (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(20) NOT NULL,
    origin VARCHAR(80) NOT NULL,
    destination VARCHAR(80) NOT NULL,
    departure_date DATE NOT NULL,
    price DOUBLE NOT NULL,
    available_seats INT NOT NULL
);

CREATE TABLE bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    flight_id BIGINT NOT NULL,
    booking_date DATE NOT NULL,
    passengers INT NOT NULL,
    children INT NOT NULL,
    total_price DOUBLE NOT NULL,

    CONSTRAINT fk_booking_flight
        FOREIGN KEY (flight_id)
        REFERENCES flights(id)
);
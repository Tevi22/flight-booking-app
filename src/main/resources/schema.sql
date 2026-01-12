CREATE TABLE IF NOT EXISTS flights (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(20) NOT NULL,
    origin VARCHAR(80) NOT NULL,
    destination VARCHAR(80) NOT NULL,
    departure_date DATE NOT NULL,
    price DOUBLE NOT NULL,
    available_seats INT NOT NULL
);
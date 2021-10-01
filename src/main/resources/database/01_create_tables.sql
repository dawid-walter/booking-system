--liquibase formatted sql
--changeset dwalter:1

CREATE TABLE ROOM
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    capacity      INT           NOT NULL,
    color         VARCHAR(50)   NOT NULL,
    description   VARCHAR(2000) NULL,
    image_url     VARCHAR(255),
    price_per_day DECIMAL(19, 2),
    title         VARCHAR(255)
);

CREATE TABLE RESERVATION
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    paid             BOOLEAN NOT NULL,
    placing_date     DATE,
    reservation_from DATE,
    reservation_to   DATE,
    room_id          BIGINT
);

ALTER TABLE RESERVATION
    ADD CONSTRAINT reservation_room_id
        FOREIGN KEY (room_id) REFERENCES room

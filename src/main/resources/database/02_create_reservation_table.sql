--liquibase formatted sql
--changeset dwalter:2

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
        FOREIGN KEY (room_id) REFERENCES room (id)

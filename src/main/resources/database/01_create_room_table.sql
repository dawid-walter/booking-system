--liquibase formatted sql
--changeset dwalter:1

CREATE TABLE ROOM
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid          CHAR(36)    NOT NULL DEFAULT '',
    capacity      INT         NOT NULL,
    color         VARCHAR(50) NOT NULL,
    description   VARCHAR(2000) NULL,
    image_url     VARCHAR(255),
    price_per_day DECIMAL(19, 2),
    title         VARCHAR(255)
);

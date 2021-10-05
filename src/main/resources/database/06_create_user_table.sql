--liquibase formatted sql
--changeset dwalter:6

CREATE TABLE USER
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid      CHAR(36)     DEFAULT '',
    username  VARCHAR(50),
    email     VARCHAR(50)  DEFAULT '',
    password  VARCHAR(60),
    user_role VARCHAR(255) DEFAULT 'USER',
    locked    BOOLEAN,
    enabled   BOOLEAN
);




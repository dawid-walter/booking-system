--liquibase formatted sql
--changeset dwalter:3

CREATE TABLE COMMENT
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(2000) NULL,
    created timestamp,
    room_id BIGINT
);

ALTER TABLE COMMENT
    ADD CONSTRAINT comment_room_id
        FOREIGN KEY (room_id) REFERENCES room (id)

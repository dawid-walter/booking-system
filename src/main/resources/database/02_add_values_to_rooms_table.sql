--liquibase formatted sql
--changeset dwalter:2

INSERT INTO `ROOM`
(`id`,`capacity`,`color`,`description`,`image_url`,`price_per_day`,`title`)
VALUES
('1', '3', 'RED', 'room 1', 'room1url', '19.99', 'room1title'),
('2', '5', 'BLUE', 'room 2', 'room2url', '19.99', 'room2title'),
('3', '2', 'YELLOW', 'room 3', 'room3url', '19.99', 'room3title'),
('4', '1', 'GREEN', 'room 4', 'room4url', '19.99', 'room4title'),
('5', '3', 'BLACK', 'room 5', 'room5url', '19.99', 'room5title');

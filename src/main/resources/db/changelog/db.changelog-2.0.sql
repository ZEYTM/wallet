--liquibase formatted sql

--changeset Dzeytov:3
INSERT INTO wallet (balance)
VALUES (3000),
       (5000);
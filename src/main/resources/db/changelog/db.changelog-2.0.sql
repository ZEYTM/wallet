--liquibase formatted sql

--changeset Dzeytov:3
INSERT INTO wallet (id, balance)
VALUES ('32bdd58e-e3dc-4332-9c89-1bdcddc204d7',5000);
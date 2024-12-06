--liquibase formatted sql

--changeset Dzeytov:1
CREATE TABLE wallet
(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    balance BIGINT NOT NULL
);

--changeset Dzeytov:2
CREATE INDEX idx_wallet_id ON wallet (id);



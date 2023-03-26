CREATE SCHEMA IF NOT EXISTS "sales";
SET SCHEMA 'sales';

CREATE TABLE IF NOT EXISTS "ORDER"
(
    id          UUID PRIMARY KEY,
    client_id   UUID           NOT NULL,
    issued_date DATE           NOT NULL,
    status      VARCHAR(10)    NOT NULL,
    total_cost  NUMERIC(12, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS ORDER_LINE
(
    id         UUID PRIMARY KEY,
    order_id   UUID           NOT NULL REFERENCES "ORDER" (id),
    product_id UUID           NOT NULL,
    quantity   NUMERIC(12, 2) NOT NULL,
    price      NUMERIC(12, 2) NOT NULL,
    cost       NUMERIC(12, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS OUTBOX
(
    id           UUID PRIMARY KEY,
    order_id     UUID      NOT NULL REFERENCES "ORDER" (id),
    created_date TIMESTAMP NOT NULL
);

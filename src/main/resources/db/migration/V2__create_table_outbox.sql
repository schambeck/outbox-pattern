CREATE TABLE "outbox"
(
    id           UUID PRIMARY KEY,
    order_id     UUID      NOT NULL REFERENCES "order" (id),
    created_date TIMESTAMP NOT NULL
);

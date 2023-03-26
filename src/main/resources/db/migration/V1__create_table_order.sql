CREATE TABLE "order"
(
    id          UUID PRIMARY KEY,
    client_id   UUID           NOT NULL,
    issued_date DATE           NOT NULL,
    status      VARCHAR(10)    NOT NULL,
    total_cost  NUMERIC(12, 2) NOT NULL
);

CREATE TABLE "order_line"
(
    id         UUID PRIMARY KEY,
    order_id   UUID           NOT NULL REFERENCES "order" (id),
    product_id UUID           NOT NULL,
    quantity   NUMERIC(12, 2) NOT NULL,
    price      NUMERIC(12, 2) NOT NULL,
    cost       NUMERIC(12, 2) NOT NULL
);

INSERT INTO "order" (id, client_id, issued_date, status, total_cost) VALUES ('b8a5e188-1a2d-4957-a5d9-d0fd227870d4', '796f5390-6a32-4f3f-a4f9-219cea1d5336', '2022-12-02', 'CREATED', 0.00);
INSERT INTO "order_line" (id, order_id, product_id, quantity, price, cost) VALUES ('465b8404-0902-437b-a367-9acd59234926', 'b8a5e188-1a2d-4957-a5d9-d0fd227870d4', '7fba7340-d24f-4548-a327-add2cd2ad4a9', 1.00, 3.00, 3.00);

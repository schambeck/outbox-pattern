# outbox-pattern
[![build](https://github.com/schambeck/outbox-pattern/actions/workflows/maven.yml/badge.svg)](https://github.com/schambeck/outbox-pattern/actions/workflows/maven.yml)

# Pattern: Transactional outbox

The **Transactional outbox** pattern implementation using an outbox table.

A separate *Message Relay* process publishes the events inserted into database to a message broker using the **Polling publisher** pattern.

# Tech Stack
- Java 17
- Spring Boot
- PostgreSQL, Flyway
- RabbitMQ
- JUnit 5, Mockito, JaCoCo, Testcontainers

# Running project

## Start infra (PostgreSQL and RabbitMQ)
```bash
$ make compose-up
```

## Build artifact
```bash
$ make dist
```

## Run backend
```bash
$ make run
```

# RabbitMQ Web Interface
```bash
$ http://localhost:15672
$ User: guest
$ Pass: guest
```

------------------------------------------------------------------------------------------
# API

#### Creating an order
<details>
<summary><code>POST</code> <code><b>/orders</b></code> <code>(creates a new order)</code></summary>

##### Payload
```json
{
  "clientId": "796f5390-6a32-4f3f-a4f9-219cea1d5336",
  "issuedDate": "2023-02-03",
  "items": [
    {
      "productId": "7fba7340-d24f-4548-a327-add2cd2ad4a9",
      "price": 3,
      "quantity": 2
    }
  ]
}
```

##### Response
**Code** : `200 CREATED`
```json
{
  "id": "7c08629a-4d23-4b6d-9363-b0f0d7303aa4",
  "clientId": "796f5390-6a32-4f3f-a4f9-219cea1d5336",
  "issuedDate": "2023-02-03",
  "status": "CREATED",
  "totalCost": 6,
  "items": [
    {
      "id": "dcb9f5fe-7423-4542-857e-47ae71322cf3",
      "productId": "7fba7340-d24f-4548-a327-add2cd2ad4a9",
      "price": 3,
      "quantity": 2,
      "cost": 6
    }
  ]
}
```
</details>
------------------------------------------------------------------------------------------

#### Closing an order
<details>
<summary><code>POST</code> <code><b>/orders/{ORDER_ID}/close</b></code> <code>(closes an existing order)</code></summary>

##### Payload
None

##### Response
**Code** : `200 CREATED`
</details>
------------------------------------------------------------------------------------------

## References
- [Pattern: Transactional outbox](https://microservices.io/patterns/data/transactional-outbox.html "A pattern language for microservices")
- [Book: Microservices patterns](https://microservices.io/book "By Chris Richardson")

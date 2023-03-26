# outbox-pattern
[![build](https://github.com/schambeck/outbox-pattern/actions/workflows/maven.yml/badge.svg)](https://github.com/schambeck/outbox-pattern/actions/workflows/maven.yml)

Outbox Design Pattern implementation.

# Tech Stack
- Java 17
- Spring Boot
- PostgreSQL, Flyway
- RabbitMQ
- JUnit 5, Mockito, JaCoCo

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

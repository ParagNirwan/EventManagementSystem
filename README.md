# Event Management System

A Spring Boot learning project exploring a REST backend for event management, using MongoDB for persistence and Spring Security for application security.

## Tech stack

- Java 8
- Spring Boot 2.7
- Spring Web
- Spring Data MongoDB
- Spring Security
- Maven

## Prerequisites

- JDK 8
- Maven
- A MongoDB instance accessible to the application

## Run locally

1. Configure the MongoDB connection in the application's configuration for your environment.
2. From the repository root, run:

   ```bash
   mvn spring-boot:run
   ```

3. The application starts on Spring Boot's configured port (8080 by default unless overridden).

## Project status

This is a learning project rather than a production-ready service. The codebase currently uses Spring Boot 2.7, an older release line. Before production use, upgrade to a supported Spring Boot release and Java version, review the security configuration, and externalize environment-specific settings.

## Build and test

```bash
mvn test
mvn package
```
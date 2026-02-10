# Project Overview

The **Speed Spares Backend** is a RESTful API built with **Spring Boot** that powers the Speed Spares e-commerce platform. It handles user authentication, product management, order processing, and workshop (taller) coordination. The application follows a monolithic, layered architecture pattern designed for maintainability and scalability.

# Purpose of the Project

The primary purpose of this backend is to serve as the central source of truth and logic for the Speed Spares ecosystem. It solves the following problems:
- Secure management of customer and workshop (taller) accounts.
- Centralized catalog of spare parts (products).
- Order lifecycle management (creation, status updates, payment processing simulation).
- Secure API communication using JWT (JSON Web Tokens).

# Technology Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.2.2
- **Build Tool**: Maven
- **Database**: MySQL (via Spring Data JPA)
- **Security**: Spring Security + JWT
- **Boilerplate Reduction**: Lombok
- **Object Mapping**: MapStruct
- **Validation**: Hibernate Validator (Spring Boot Starter Validation)

# Architecture Overview

The project follows a **Traditional Layered Architecture**. The code is organized by technical function, separating concerns into distinct layers:

1.  **Presentation Layer (Controllers)**: Handles HTTP requests and responses.
2.  **Service Layer**: Contains business logic and transaction management.
3.  **Persistence Layer (Repositories)**: Handles database interactions.
4.  **Domain Layer (Entities)**: Represents the core business objects.

# Project Structure

The source code is located in `src/main/java/com/speedspares`.

```text
com.speedspares
├── config/           # Configuration classes (e.g., SecurityConfig)
├── controller/       # REST Controllers (API endpoints)
├── dto/              # Data Transfer Objects (contracts for API I/O)
├── exception/        # Global exception handling advice
├── mapper/           # MapStruct interfaces for converting Entity <-> DTO
├── model/            # Domain entities (JPA annotated classes)
│   └── entity/       # Specific entity classes (Usuario, Producto, Pedido, etc.)
├── repository/       # Spring Data JPA interfaces
├── security/         # JWT implementation (filters, providers)
└── service/          # Business logic classes
```

# Layer Responsibilities

-   **Controller (`com.speedspares.controller`)**:
    -   Entry point for HTTP requests.
    -   Handles input validation (`@Valid`).
    -   Delegates business logic to Services.
    -   Returns DTOs wrapped in `ResponseEntity`.
    -   **Rule**: Controllers should never contain complex business logic or database queries.

-   **Service (`com.speedspares.service`)**:
    -   Implement business rules (e.g., calculating totals, verifying user status).
    -   Orchestrate transactional operations (`@Transactional`).
    -   Access data via Repositories.
    -   **Rule**: Services accept and return DTOs or DTO-ready data (mapped via Mappers).

-   **Repository (`com.speedspares.repository`)**:
    -   Interfaces extending `JpaRepository`.
    -   Define custom database queries using method names or `@Query`.
    -   **Rule**: Only repositories communicate directly with the database.

-   **Model/Entity (`com.speedspares.model.entity`)**:
    -   Represent database tables.
    -   Annotated with `@Entity`, `@Table`, `@Id`, etc.
    -   **Rule**: Strong encapsulation is preferred, though Lombok `@Data` is used for convenience.

-   **DTO (`com.speedspares.dto`)**:
    -   Plain Java objects carrying data between processes.
    -   Decouple the API contract from the internal database schema.

-   **Mapper (`com.speedspares.mapper`)**:
    -   Declarative interfaces for MapStruct.
    -   Auto-generate implementation for converting between Entity and DTO.

# Request Flow

A typical request (e.g., creating an order) follows this path:

1.  **Request** enters the application.
2.  **`JwtAuthenticationFilter`** intercepts the request to validate the JWT token and set the `SecurityContext`.
3.  **`PedidoController`** receives the POST request.
    -   Validates the `PedidoDTO.Request` body.
    -   Calls `PedidoService.crearPedido()`.
4.  **`PedidoService`** executes:
    -   Retrieves current user from `SecurityContext`.
    -   Calls `ProductoRepository` and `UsuarioRepository` to fetch entities.
    -   Calculates totals (business logic).
    -   Saves the new `Pedido` entity via `PedidoRepository`.
    -   Converts the saved entity back to `PedidoDTO` using `PedidoMapper`.
5.  **`PedidoController`** returns the `PedidoDTO` in the HTTP response.

# Conventions and Rules

-   **Language**: Code is written in **English** (class names, variable names), but some domain terms might be in **Spanish** (e.g., `Pedido`, `Taller`, `Usuario`) to match the business requirements or existing database schema.
    -   *Note*: The current codebase uses Spanish for Entity and Service names (`PedidoService`, `Productos`). Maintain this consistency.
-   **Lombok**: Use `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` to reduce boilerplate.
-   **Mapping**: **Always** use `MapStruct` mappers. Do not manually set fields in controllers or services for conversion.
-   **Security**: Always retrieve the authenticated user from `SecurityContextHolder` within the Service layer, not the Controller.
-   **Transactions**: Use `@Transactional` on Service methods that modify data (create, update, delete).

# Guidelines for AI Agents

When modifying this project, adhere to the following:

-   **Safe to Modify**:
    -   **Controllers**: Adding new endpoints. Ensure you use `@RequestMapping` and proper HTTP verbs.
    -   **Services**: Adding business logic. Verify you are using the correct Repository.
    -   **DTOs**: Adding fields. Remember to update the Entity and Mapper if necessary.
    -   **Repositories**: Adding custom find methods (e.g., `findByNombre(String nombre)`).

-   **Handle with Care (High Risk)**:
    -   **`SecurityConfig` & `com.speedspares.security`**: Changes here can break authentication for the entire app. Only modify if you understand the Spring Security filter chain.
    -   **Entity Relationships**: Modifying `@OneToMany`, `@ManyToOne`, etc., in `com.speedspares.model.entity` can break database integrity or lazy loading. Ensure you update the database schema (or allow Hibernate ddl-auto to handle it if enabled for dev).
    -   **Pom.xml**: Adding dependencies is fine, but upgrading versions (especially Spring Boot) requires checking compatibility.

-   **Code Generation Style**:
    -   When creating a new feature (e.g., "Review"), follow the pattern:
        1.  Create `Review` Entity (`model/entity`).
        2.  Create `ReviewRepository` (`repository`).
        3.  Create `ReviewDTO` (`dto`).
        4.  Create `ReviewMapper` (`mapper`).
        5.  Create `ReviewService` (`service`).
        6.  Create `ReviewController` (`controller`).

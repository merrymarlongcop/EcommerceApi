# E-Commerce API Security and Validation

## Security Architecture

This project uses Session-Based Authentication with Spring Security.

When a user logs in successfully, the server creates a session and sends a JSESSIONID cookie to the browser. The browser automatically sends this cookie with every request to protected endpoints.

The system uses role-based access control:
- ADMIN users can delete products.
- Authenticated users can create and update products.
- Public users can view products.

Spring Security handles login, logout, and session management.

---

## Validation Rules

The project uses Bean Validation annotations for validating requests.

Validation constraints include:
- @NotBlank for required text fields
- @NotNull for required values
- @Positive for prices and quantities
- @Size for username and password length
- @Email for email validation

Validation is handled using DTOs and @Valid annotations.

---

## API Reference

### Public Endpoints

- GET /api/products
- GET /api/products/{id}
- POST /api/auth/register
- GET /login

### Authenticated Endpoints

- POST /api/products
- PUT /api/products/{id}

### Admin Only Endpoints

- DELETE /api/products/{id}

---

## Code Quality

- Security configurations are properly commented.
- Validation error messages are user-friendly.
- Global exception handling is implemented.
- DTOs are used for request validation.
- Session-based authentication is implemented using Spring Security.

---

## Technologies Used

- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Gradle
- Thunder Client
- H2 Database / MySQL

---

## Testing

The following were tested successfully:
- User registration
- Login and logout
- Protected endpoints
- Role-based access
- Validation errors
- Session handling
# User Service — Spring Boot

A simple user management microservice built with Spring Boot 2.7.18. Supports user creation, update, deletion, and lookup by email. Uses H2 in-memory database for development and testing.

## Tech Stack

- Spring Boot 2.7.18
- Java 11
- Spring Data JPA
- Spring Security
- H2 Database (in-memory)
- Bean Validation (JSR-380)
- Maven

## Dependencies (from `pom.xml`)

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.18</version>
</parent>

<properties>
    <java.version>11</java.version>
</properties>

<dependencies>
    <!-- Core Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <!-- Web + REST -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- JPA + H2 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>


## Setup Instructions
git clone https://github.com/your-username/user-service-springboot.git
cd user-service-springboot
mvn clean install
mvn spring-boot:run
The application will start on `http://localhost:8080`.
## Access H2 Console
Navigate to `http://localhost:8080/h2-console` to access the H2 database console.
## API Endpoints

| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| POST   | `/api/users`         | Create a new user        |
| PUT    | `/api/users/{id}`    | Update user by ID        |
| DELETE | `/api/users/{id}`    | Delete user by ID        |
| GET    | `/api/users/search`  | Find user by email       |
| GET    | `/api/users`         | List all users           |
## Example Requests
### Create User
```bash 
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "jane.doe@example.com",
    "password": "securePass123",
    "name": "Jane Doe"
  }'

### Find User by Email
```bash
curl -X GET "http://localhost:8080/api/users/search?email=jane.doe@example.com"
### Update User
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "email": "jane.new@example.com",
    "password": "newSecurePass456",
    "name": "Jane Updated"
  }'
### Delete User
```bash
curl -X DELETE http://localhost:8080/api/users/1
### List All Users
```bash
curl -X GET http://localhost:8080/api/users






# User Service — Spring Boot

A simple user management microservice built with Spring Boot 2.7.18. It supports user creation, update, deletion, and lookup by email. The service uses an H2 in-memory database for development and testing.

---

## Tech Stack

- Spring Boot 2.7.18
- Java 11
- Spring Data JPA
- Spring Security
- H2 Database (in-memory)
- Bean Validation (JSR-380)
- Maven

---

## Project Dependencies

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.18</version>
        <relativePath/>
    </parent>

    <groupId>com.ntsabelle</groupId>
    <artifactId>user-service-springboot</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>user-service-springboot</name>
    <description>User Service Spring Boot Application</description>

    <properties>
        <java.version>11</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
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

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## Setup Instructions

```bash
git clone https://github.com/your-username/user-service-springboot.git
cd user-service-springboot
mvn clean install
mvn spring-boot:run
```

The application will start at:  
`http://localhost:8080`

---

## Access H2 Console

To view the in-memory database, navigate to:  
`http://localhost:8080/h2-console`

Use the following credentials:
- JDBC URL: `jdbc:h2:mem:userdb`
- Username
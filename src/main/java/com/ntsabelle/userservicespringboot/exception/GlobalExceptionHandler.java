package com.ntsabelle.userservicespringboot.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Centralized exception handling for REST controllers and services.
 * Converts common exceptions into meaningful HTTP responses
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors triggered by @Valid annotations in request DTOs.
     * Aggregates all field errors into a single readable message.
     *
     * @param ex the exception containing validation failure details
     * @return 400 Bad Request with field-specific error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body("Validation failed: " + errorMsg);
    }

    /**
     * Handles database constraint violations (e.g., unique key conflicts).
     * Extracts the root cause message for clarity.
     *
     * @param ex the exception thrown by JPA or the database
     * @return 500 Internal Server Error with database error details
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataErrors(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database error: " + ex.getRootCause().getMessage());
    }

    /**
     * Handles cases where a user is not found in the system.
     *
     * @param ex the custom UserNotFoundException
     * @return 404 Not Found with a descriptive message
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles attempts to create a user with an email that already exists.
     *
     * @param ex the custom EmailAlreadyExistsException
     * @return 409 Conflict with a descriptive message
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailConflict(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}

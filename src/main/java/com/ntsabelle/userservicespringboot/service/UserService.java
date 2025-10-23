package com.ntsabelle.userservicespringboot.service;

import com.ntsabelle.userservicespringboot.dto.CreateUserRequest;
import com.ntsabelle.userservicespringboot.dto.UpdateUserRequest;
import com.ntsabelle.userservicespringboot.dto.UserResponse;
import com.ntsabelle.userservicespringboot.exception.EmailAlreadyExistsException;
import com.ntsabelle.userservicespringboot.exception.UserNotFoundException;
import com.ntsabelle.userservicespringboot.model.User;
import com.ntsabelle.userservicespringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for managing user operations.
 * Handles business logic and interacts with the UserRepository.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * Creates a new user if the email is not already registered.
     * Encodes the password before saving.
     *
     * @param req DTO containing email, password, and name
     * @return UserResponse DTO with saved user details
     * @throws EmailAlreadyExistsException if email is already in use
     */
    public UserResponse createUser(CreateUserRequest req) {
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new EmailAlreadyExistsException("Email '" + req.getEmail() + "' is already registered");
        }
        User user = new User(req.getEmail(), encoder.encode(req.getPassword()), req.getName());
        return UserResponse.from(userRepo.save(user));
    }

    /**
     * Updates an existing user's details.
     * Validates email uniqueness if changed.
     * Encodes password if updated.
     *
     * @param id  User ID to update
     * @param req DTO containing updated fields
     * @return UserResponse DTO with updated user details
     * @throws UserNotFoundException if user ID does not exist
     */
    public UserResponse updateUser(Long id, UpdateUserRequest req) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        if (req.getEmail() != null && !req.getEmail().equals(user.getEmail())) {
            if (userRepo.existsByEmail(req.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            user.setEmail(req.getEmail());
        }

        if (req.getPassword() != null) {
            user.setPassword(encoder.encode(req.getPassword()));
        }

        if (req.getName() != null) {
            user.setName(req.getName());
        }

        return UserResponse.from(userRepo.save(user));
    }

    /**
     * Deletes a user by ID.
     * Validates existence before deletion.
     *
     * @param id User ID to delete
     * @throws UserNotFoundException if user ID does not exist
     */
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException("Cannot delete — user with ID " + id + " not found");
        }
        userRepo.deleteById(id);
    }

    /**
     * Finds a user by email.
     *
     * @param email Email to search
     * @return UserResponse DTO with user details
     * @throws UserNotFoundException if no user is found with the given email
     */
    public UserResponse findByEmail(String email) {
        return UserResponse.from(userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email '" + email + "' not found")));
    }

    /**
     * Lists all users in the system.
     *
     * @return List of UserResponse DTOs
     */
    public List<UserResponse> listUsers() {
        return userRepo.findAll().stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }
}

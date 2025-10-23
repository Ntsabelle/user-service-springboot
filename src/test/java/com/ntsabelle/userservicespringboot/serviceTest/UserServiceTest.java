package com.ntsabelle.userservicespringboot.serviceTest;

import com.ntsabelle.userservicespringboot.dto.CreateUserRequest;
import com.ntsabelle.userservicespringboot.dto.UserResponse;
import com.ntsabelle.userservicespringboot.model.User;
import com.ntsabelle.userservicespringboot.repository.UserRepository;
import com.ntsabelle.userservicespringboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_shouldSaveAndReturnUserResponse() {
        // Arrange
        CreateUserRequest req = new CreateUserRequest("test@example.com", "password123", "Test User");
        when(userRepo.existsByEmail(req.getEmail())).thenReturn(false);
        when(encoder.encode(req.getPassword())).thenReturn("encodedPassword");

        User savedUser = new User(req.getEmail(), "encodedPassword", req.getName());
        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserResponse response = userService.createUser(req);

        // Assert
        assertEquals(req.getEmail(), response.getEmail());
        assertEquals(req.getName(), response.getName());
        verify(userRepo).save(any(User.class));
    }
}

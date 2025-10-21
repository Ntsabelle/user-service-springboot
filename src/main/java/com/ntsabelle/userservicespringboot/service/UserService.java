package com.ntsabelle.userservicespringboot.service;

import com.ntsabelle.userservicespringboot.dto.CreateUserRequest;
import com.ntsabelle.userservicespringboot.dto.UpdateUserRequest;
import com.ntsabelle.userservicespringboot.dto.UserResponse;
import com.ntsabelle.userservicespringboot.model.User;
import com.ntsabelle.userservicespringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepo;
    private PasswordEncoder encoder;

    public UserResponse createUser(CreateUserRequest req) {
        if (userRepo.existsByEmail(req.getEmail())) throw new RuntimeException("Email already exists");
        User user = new User(req.getEmail(), encoder.encode(req.getPassword()), req.getName());
        return UserResponse.from(userRepo.save(user));
    }

    public UserResponse updateUser(Long id, UpdateUserRequest req) {
        User user = userRepo.findById(id).orElseThrow();
        if (req.getEmail() != null && !req.getEmail().equals(user.getEmail())) {
            if (userRepo.existsByEmail(req.getEmail())) throw new RuntimeException("Email already exists");
            user.setEmail(req.getEmail());
        }
        if (req.getPassword() != null) user.setPassword(encoder.encode(req.getPassword()));
        if (req.getName() != null) user.setName(req.getName());
        return UserResponse.from(userRepo.save(user));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public UserResponse findByEmail(String email) {
        return UserResponse.from(userRepo.findByEmail(email).orElseThrow());
    }

    public List<UserResponse> listUsers() {
        return userRepo.findAll().stream().map(UserResponse::from).collect(Collectors.toList());
    }
}


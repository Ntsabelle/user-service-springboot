package com.ntsabelle.userservicespringboot.dto;

import jakarta.validation.constraints.*;

public class UpdateUserRequest {
    @Email
    private String email;

    @Size(min = 8, max = 16)
    private String password;

    private String name;

    public UpdateUserRequest() {
        // Default constructor
    }

    public UpdateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

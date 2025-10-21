package com.ntsabelle.userservicespringboot.dto;

import com.ntsabelle.userservicespringboot.model.User;

public class UserResponse {
    private Long id;
    private String email;
    private String name;

    public UserResponse(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }
}

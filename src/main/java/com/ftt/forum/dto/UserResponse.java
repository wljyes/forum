package com.ftt.forum.dto;

import com.ftt.forum.entity.User;

import java.util.Optional;

public class UserResponse {
    public int id = -1;
    public String username = "";

    public UserResponse() {}

    public UserResponse(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UserResponse of(User user) {
        UserResponse response = new UserResponse();
        Optional.ofNullable(user).ifPresent(u -> {
            response.id = u.getId();
            response.username = u.getUsername();
        });
        return response;
    }
}

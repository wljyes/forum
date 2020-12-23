package com.ftt.forum.dto;

import com.ftt.forum.entity.User;

public class UserResponse {
    public final int id;
    public final String username;

    public UserResponse(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}

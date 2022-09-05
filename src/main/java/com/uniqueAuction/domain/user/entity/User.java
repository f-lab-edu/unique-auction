package com.uniqueAuction.domain.user.entity;

import lombok.*;

@Getter
@Setter
@ToString
public class User {
    private Long userId;
    private String email;
    private String password;
    private String username;
    private String phone;
    private Role role;

    @Builder
    public User(String email, String password, String username, String phone, Role role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.role = role;
    }
}

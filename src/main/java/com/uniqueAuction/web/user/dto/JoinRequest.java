package com.uniqueAuction.web.user.dto;

import com.uniqueAuction.domain.user.entity.Role;
import com.uniqueAuction.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequest {
    private Long userId;
    private String email;
    private String password;
    private String username;
    private String phone;

    public User toUser() {
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .role(Role.CUSTOMER)
                .build();
    }

    @Builder
    public JoinRequest(String email, String password, String username, String phone) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
    }
}

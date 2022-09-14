package com.uniqueAuction.web.user.request;

import com.uniqueAuction.domain.user.entity.Role;
import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.util.validator.annotation.Phone;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class JoinRequest {
    private Long userId;
    private Boolean isAdmin;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", message = "비밀번호는 최소 8글자, 최대 16자 이며 영문 대소문자 1개, 숫자 1개가 각각 포함된 형태이어야 합니다.")
    private String password;

    @NotBlank(message = "유저명은 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9]{4,12}$", message = "유저명 형식에 맞지 않습니다.")
    private String username;

    @NotBlank(message = "휴대폰은 필수 입력 값입니다.")
    @Phone()
    private String phone;

    public JoinRequest() {
        this.isAdmin = false;
    }

    public User toEntity(JoinRequest joinRequest) {

        Role requestRole = Role.CUSTOMER;

        if (joinRequest.getIsAdmin()) {
            requestRole = Role.ADMIN;
        }

        return User.builder()
                .email(joinRequest.getEmail())
                .username(joinRequest.getUsername())
                .encodedPassword(joinRequest.getPassword())
                .phone(joinRequest.getPhone())
                .role(requestRole)
                .build();
    }

}

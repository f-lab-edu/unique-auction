package com.uniqueAuction.domain.user.entity;

import com.uniqueAuction.web.product.request.ProductSaveRequest;
import lombok.*;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String email;
    private String encodedPassword;
    private String username;
    private String phone;

    /* Role setter 생성 제외 */
    @Setter(AccessLevel.NONE)
    private Role role;

    @Builder
    public User(String email, String encodedPassword, String username, String phone, Role role) {
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.username = username;
        this.phone = phone;
        this.role = role;
    }


}

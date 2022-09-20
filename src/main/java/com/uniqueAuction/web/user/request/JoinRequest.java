package com.uniqueAuction.web.user.request;

import com.uniqueAuction.domain.user.entity.Role;
import com.uniqueAuction.domain.user.entity.User;
import com.utils.annotation.Email;
import com.utils.annotation.Password;
import com.utils.annotation.Phone;
import com.utils.annotation.Username;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class JoinRequest {
	private Long userId;
	private Boolean isAdmin;

	@Email
	private String email;

	@Password
	private String password;

	@Username
	private String username;

	@Phone
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

package com.uniqueAuction.web.user.request;

import com.uniqueAuction.domain.user.entity.User;
import com.utils.annotation.Email;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
	private Long userId;

	@Email
	private String email;

	private String password;

	private String username;

	private String phone;

	public User toEntity(UpdateUserRequest updateUserRequest) {

		return User.builder()
			.email(updateUserRequest.getEmail())
			.username(updateUserRequest.getUsername())
			.encodedPassword(updateUserRequest.getPassword())
			.phone(updateUserRequest.getPhone())
			.build();
	}

}

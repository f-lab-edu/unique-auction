package com.uniqueauction.web.user.request;

import static com.utils.RegExpCode.*;

import com.uniqueauction.domain.user.entity.User;
import com.utils.annotation.RegExp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserRequest {
	private Long userId;

	@RegExp(regExpCode = EMAIL)
	private String email;

	@RegExp(regExpCode = PASSWORD)
	private String password;

	@RegExp(regExpCode = USERNAME)
	private String username;

	@RegExp(regExpCode = PHONE)
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

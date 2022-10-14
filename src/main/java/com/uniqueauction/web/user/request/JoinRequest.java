package com.uniqueauction.web.user.request;

import static com.utils.RegExpCode.*;

import com.uniqueauction.domain.user.entity.Role;
import com.uniqueauction.domain.user.entity.User;
import com.utils.annotation.RegExp;

import lombok.Getter;

@Getter
public class JoinRequest {
	private Long userId;
	private Boolean isAdmin;

	@RegExp(regExpCode = EMAIL)
	private String email;

	@RegExp(regExpCode = PASSWORD)
	private String password;

	@RegExp(regExpCode = USERNAME)
	private String username;

	@RegExp(regExpCode = PHONE)
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
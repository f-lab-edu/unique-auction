package com.uniqueauction.web.user.request;

import static com.utils.RegExpCode.*;

import com.uniqueauction.domain.user.entity.Role;
import com.uniqueauction.domain.user.entity.User;
import com.utils.annotation.RegExp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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

	public User convert(JoinRequest joinRequest) {

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

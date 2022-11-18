package com.uniqueauction.web.user.request;

import static com.utils.RegExpCode.*;

import com.uniqueauction.domain.user.entity.Role;
import com.uniqueauction.domain.user.entity.User;
import com.utils.annotation.RegExp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JoinRequest {
	private Boolean isAdmin;

	@RegExp(regExpCode = EMAIL)
	private String email;

	@RegExp(regExpCode = PASSWORD)
	private String password;

	@RegExp(regExpCode = USERNAME)
	private String username;

	@RegExp(regExpCode = PHONE)
	private String phone;

	public User convert() {

		Role requestRole = Role.CUSTOMER;

		if (this.getIsAdmin()) {
			requestRole = Role.ADMIN;
		}

		return User.builder()
			.email(getEmail())
			.username(getUsername())
			.phone(getPhone())
			.role(requestRole)
			.build();
	}

}

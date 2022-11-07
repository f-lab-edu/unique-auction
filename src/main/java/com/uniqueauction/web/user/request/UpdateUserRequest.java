package com.uniqueauction.web.user.request;

import static com.utils.RegExpCode.*;

import com.uniqueauction.domain.user.entity.User;
import com.utils.annotation.RegExp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateUserRequest {

	@RegExp(regExpCode = EMAIL)
	private String email;

	@RegExp(regExpCode = PASSWORD)
	private String password;

	@RegExp(regExpCode = USERNAME)
	private String username;

	@RegExp(regExpCode = PHONE)
	private String phone;

	public User toEntity() {

		return User.builder()
			.email(this.getEmail())
			.username(this.getUsername())
			.encodedPassword(this.getPassword())
			.phone(this.getPhone())
			.build();
	}

}

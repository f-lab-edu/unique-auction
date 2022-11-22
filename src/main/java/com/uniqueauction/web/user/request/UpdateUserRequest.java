package com.uniqueauction.web.user.request;

import static com.utils.RegExpCode.*;

import com.uniqueauction.domain.user.entity.User;
import com.utils.annotation.RegExp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
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

	public User convert() {

		return User.builder()
			.email(this.getEmail())
			.username(this.getUsername())
			.phone(this.getPhone())
			.build();
	}

	public void setEncryptPassword(String password) {
		this.password = password;
	}

}

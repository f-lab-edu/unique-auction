package com.uniqueAuction.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class User {
	private String email;
	private String username;
	private String phone;
	private Role role;

	@Setter
	private Long id;
	@Setter
	private String encodedPassword;

	@Builder
	public User(String email, String encodedPassword, String username, String phone, Role role) {
		this.email = email;
		this.encodedPassword = encodedPassword;
		this.username = username;
		this.phone = phone;
		this.role = role;
	}
}

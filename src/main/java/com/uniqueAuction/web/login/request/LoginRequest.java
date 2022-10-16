package com.uniqueauction.web.login.request;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class LoginRequest {

	@NotBlank(message = "공백은 입력할 수 없습니다.")
	private String email;

	@Length(min = 8, message = "비밀번호는 최소 8자리 이상이어야 합니다.")
	@NotBlank(message = "공백은 입력할 수 없습니다.")
	private String password;
}

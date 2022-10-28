package com.uniqueauction.web.login.request;

import static com.utils.RegExpCode.*;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.utils.annotation.RegExp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@RegExp(regExpCode = EMAIL)
	private String email;

	@RegExp(regExpCode = PASSWORD)
	private String password;
}

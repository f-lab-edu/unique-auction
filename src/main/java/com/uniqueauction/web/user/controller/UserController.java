package com.uniqueauction.web.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.service.EncryptService;
import com.uniqueauction.domain.user.service.UserService;
import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.user.request.JoinRequest;
import com.uniqueauction.web.user.request.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final EncryptService encryptService;

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse joinUser(@RequestBody @Validated JoinRequest joinRequest, BindingResult result) {
		User user = joinRequest.convert();
		user.setEncodedPassword(encryptService.encrypt(joinRequest.getPassword()));
		userService.join(user);
		return CommonResponse.success();
	}

	@PatchMapping("/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse updateUser(@RequestBody @Validated UpdateUserRequest updateUserRequest,
		BindingResult result) {
		userService.update(updateUserRequest.toEntity());
		return CommonResponse.success();
	}
}

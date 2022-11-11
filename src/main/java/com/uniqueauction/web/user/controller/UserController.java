package com.uniqueauction.web.user.controller;

import static com.uniqueauction.domain.user.entity.Role.*;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.aop.LoginCheck;
import com.uniqueauction.domain.user.service.UserService;
import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.user.request.JoinRequest;
import com.uniqueauction.web.user.request.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse joinUser(@RequestBody @Validated JoinRequest joinRequest, BindingResult result) {
		userService.join(joinRequest);
		return CommonResponse.success();
	}

	@LoginCheck(type = CUSTOMER)
	@PatchMapping("/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse updateUser(@PathVariable("id") String id,
		@RequestBody @Validated UpdateUserRequest updateUserRequest, BindingResult result) {
		userService.update(updateUserRequest);
		return CommonResponse.success();
	}
}

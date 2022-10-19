package com.uniqueauction.web.user.controller;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.user.service.UserService;
import com.uniqueauction.exception.advice.CommonValidationException;
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

		if (result.hasErrors()) {
			throw new CommonValidationException(MISSING_PARAMETER);
		}
		userService.join(joinRequest);
		return CommonResponse.success();
	}

	@PatchMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse updateUser(@RequestBody @Validated UpdateUserRequest updateUserRequest,
		BindingResult result) {

		if (result.hasErrors()) {
			throw new CommonValidationException(MISSING_PARAMETER);
		}

		userService.update(updateUserRequest.getUserId());
		return CommonResponse.success();
	}
}

//package com.uniqueAuction.web.user.controller;
//
//import com.uniqueAuction.domain.user.service.UserService;
//import com.uniqueAuction.web.user.request.JoinRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.uniqueAuction.domain.user.service.UserService;
//import com.uniqueAuction.exception.advice.user.UserException;
//import com.uniqueAuction.web.response.CommonResponse;
//import com.uniqueAuction.web.user.request.JoinRequest;
//import com.uniqueAuction.web.user.request.UpdateUserRequest;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//	@PostMapping("/users")
//	@ResponseStatus(HttpStatus.CREATED)
//	public CommonResponse joinUser(@RequestBody @Validated JoinRequest joinRequest, BindingResult result) {
//		if (result.hasErrors()) {
//			throw new UserException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
//		}
//		userService.join(joinRequest);
//		return CommonResponse.success("회원가입 성공");
//	}
//
//	@PatchMapping("/users")
//	@ResponseStatus(HttpStatus.OK)
//	public CommonResponse updateUser(@RequestBody @Validated UpdateUserRequest updateUserRequest,
//		BindingResult result) {
//		if (result.hasErrors()) {
//			throw new UserException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
//		}
//		userService.update(updateUserRequest);
//		return CommonResponse.success("회원정보 수정 성공");
//	}
//}

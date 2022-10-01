package com.uniqueAuction.web.user.controller;

import com.uniqueAuction.domain.user.service.UserService;
import com.uniqueAuction.exception.advice.CommonValidationException;
import com.uniqueAuction.web.response.CommonResponse;
import com.uniqueAuction.web.user.request.JoinRequest;
import com.uniqueAuction.web.user.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.uniqueAuction.exception.ErrorCode.MISSING_PARAMETER;

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
        return CommonResponse.success("회원가입 성공");
    }

    @PatchMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse updateUser(@RequestBody @Validated UpdateUserRequest updateUserRequest,
                                     BindingResult result) {

        if (result.hasErrors()) {
            throw new CommonValidationException(MISSING_PARAMETER);
        }

        userService.update(updateUserRequest);
        return CommonResponse.success("회원정보 수정 성공");
    }
}

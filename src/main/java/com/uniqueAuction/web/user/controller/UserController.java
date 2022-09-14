package com.uniqueAuction.web.user.controller;

import com.uniqueAuction.domain.user.service.UserService;
import com.uniqueAuction.web.user.request.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Void> joinUser(@RequestBody @Validated JoinRequest joinRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        userService.join(joinRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

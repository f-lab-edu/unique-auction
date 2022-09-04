package com.uniqueAuction.web.user.controller;

import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.service.UserService;
import com.uniqueAuction.web.user.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Void> signInUser(@RequestBody SignupRequest signupRequest) {
        userService.join(signupRequest.toUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

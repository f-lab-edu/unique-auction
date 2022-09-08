package com.uniqueAuction.web.user.controller;

import com.uniqueAuction.domain.user.service.UserService;
import com.uniqueAuction.web.user.request.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Void> joinUser(@RequestBody JoinRequest joinRequest) {
        userService.join(joinRequest.toUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users")
    public ResponseEntity<Void> findById(@RequestBody JoinRequest joinRequest) {
        userService.findById(joinRequest.toUser().getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

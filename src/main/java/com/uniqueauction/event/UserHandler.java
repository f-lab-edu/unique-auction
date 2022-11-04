package com.uniqueauction.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserHandler {

	private final UserService userService;

	@EventListener
	public void findUser(UserEvent foundUserEvent) {
		User findUser = userService.findById(foundUserEvent.getUserId());
		foundUserEvent.setUser(findUser);
	}

}

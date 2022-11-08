package com.uniqueauction.event;

import com.uniqueauction.domain.user.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class UserEvent {

	private Long userId;

	@Setter
	private User user;

	public UserEvent(Long userId) {
		this.userId = userId;
	}

	public static UserEvent of(Long userId) {
		return new UserEvent(userId);
	}
}

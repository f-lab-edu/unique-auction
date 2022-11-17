package com.uniqueauction.domain.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uniqueauction.domain.user.entity.Role;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.web.user.request.JoinRequest;
import com.uniqueauction.web.user.request.UpdateUserRequest;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Mock
	EncryptService encryptService;

	@Test
	@DisplayName("회원가입성공 테스트")
	void userJoinTest() {

		doReturn(false).when(userRepository).existsByEmail(anyString());
		doReturn("@@@@@@@@@@@").when(encryptService).encrypt(anyString());
		doReturn(getUser()).when(userRepository).save(any(User.class));

		userService.join(getJoinReq());

		verify(userRepository).save(getUser());

	}

	@Test
	@DisplayName("회원가입실패 테스트(중복유저)")
	void userJoinFailDuplicateUserTest() {

		doReturn(true).when(userRepository).existsByEmail(anyString());

		assertThatThrownBy(
			() -> userService.join(getJoinReq())
		).isInstanceOf(CommonException.class);

	}

	@Test
	@DisplayName("업데이트 테스트")
	void userUpdateTest() {

		doReturn(getUpdateReq().toEntity()).when(userRepository).findByEmail(anyString());
		doReturn("@@@@@@@@@@@").when(encryptService).encrypt(anyString());

		User user = userService.update(getUpdateReq());

		assertThat(user.getEmail()).isEqualTo("test33@test.com");
		assertThat(user.getEncodedPassword()).isEqualTo("@@@@@@@@@@@");
		assertThat(user.getUsername()).isEqualTo("변경된유저");
		assertThat(user.getPhone()).isEqualTo("01012345678");
	}

	public User getUser() {
		return User.builder()
			.email("test@test.com")
			.encodedPassword("@@@@@@@@@@@")
			.username("테스트유저")
			.phone("01012341234")
			.role(Role.CUSTOMER)
			.build();
	}

	public JoinRequest getJoinReq() {
		return JoinRequest.builder()
			.email("test@test.com")
			.password("12345678aA")
			.username("테스트유저")
			.phone("01012341234")
			.isAdmin(false)
			.build();
	}

	public UpdateUserRequest getUpdateReq() {
		return UpdateUserRequest.builder()
			.email("test33@test.com")
			.password("1345345A")
			.username("변경된유저")
			.phone("01012345678")
			.build();
	}
}

package com.uniqueauction.domain.login;

import static com.uniqueauction.domain.user.entity.Role.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import com.uniqueauction.AbstractContainerBaseTest;
import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.login.service.LoginService;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.domain.user.service.EncryptService;
import com.uniqueauction.exception.advice.CommonNotFoundException;
import com.uniqueauction.web.login.request.LoginRequest;

@TestContainerBase
public class LoginServiceTest {

	@Spy
	@InjectMocks
	private LoginService loginService;

	@Mock
	private EncryptService encryptService;

	@Mock
	private HttpSession session;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void set() {
		session = mock(HttpSession.class);
	}

	@Test
	@DisplayName("로그인 성공시 예외가발생하지 않는다")
	void loginServiceSuccess() {

		doReturn(anyUser(getLoginRequest())).when(userRepository).findAll();
		doReturn("@@@@@@@@@@").when(encryptService).encrypt(getLoginRequest().getPassword());

		loginService.login(getLoginRequest());

	}

	@Test
	@DisplayName("로그인 실패시(계정이없는경우) 예외 출력")
	void loginServiceFail() {

		doReturn(anyUser(getLoginRequest())).when(userRepository).findAll();
		doReturn("@@@@@@@@@@").when(encryptService).encrypt(getNotUserRequest().getPassword());

		assertThatThrownBy(
			() ->
				loginService.login(getNotUserRequest())
		).isInstanceOf(CommonNotFoundException.class);

	}

	private List<User> anyUser(LoginRequest loginRequest) {
		List<User> list = new ArrayList<>();
		list.add(getUser(loginRequest));
		list.get(0).setId(1L);
		return list;
	}

	private User getUser(LoginRequest loginRequest) {
		return User.builder()
			.email("email@email.com")
			.encodedPassword("@@@@@@@@@@")
			.phone("010-1234-1344")
			.role(ADMIN)
			.build();
	}

	private LoginRequest getLoginRequest() {
		return new LoginRequest("email@email.com", "123");
	}

	private LoginRequest getNotUserRequest() {
		return new LoginRequest("email2@email.com", "1232");
	}
}

package com.uniqueauction.domain.login;

import static com.uniqueauction.domain.user.entity.Role.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import com.uniqueauction.domain.login.service.LoginService;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.domain.user.service.EncryptService;
import com.uniqueauction.exception.advice.CommonNotFoundException;
import com.uniqueauction.web.login.request.LoginRequest;

@ExtendWith(MockitoExtension.class) //  Mockito를 사용하려면 붙여줘야되 어느테이션
public class LoginServiceTest {

	@Spy
	@InjectMocks
	private LoginService loginService;

	@Mock
	private EncryptService encryptService;

	protected MockHttpSession session;
	protected MockHttpServletRequest request;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void set() {

		session = new MockHttpSession();
		session.setAttribute("MEMBER_USER", 1);

		//
		request = new MockHttpServletRequest();
		request.setSession(session);

		loginService = new LoginService(userRepository, session, encryptService);
	}

	@Test
	@DisplayName("로그인 성공시 예외가발생하지 않는다")
	void loginServiceSuccess() {

		doReturn("@@@@@@@@@@").when(encryptService).encrypt(anyString());

		User user = getUser();

		doReturn(Optional.ofNullable(user)).when(userRepository)
			.findByEmailAndEncodedPassword(getLoginRequest().getEmail(), getLoginRequest().getPassword());

		loginService.login(getLoginRequest());

	}

	@Test
	@DisplayName("로그인 실패시(계정이없는경우) 예외 출력")
	void loginServiceFail() {

		doReturn("@@@@@@@@@@").when(encryptService).encrypt(anyString());

		doReturn(Optional.empty()).when(userRepository)
			.findByEmailAndEncodedPassword(anyString(), anyString());

		assertThatThrownBy(
			() ->
				loginService.login(getLoginRequest())
		).isInstanceOf(CommonNotFoundException.class);

	}

	private User getUser() {
		return User.builder()
			.email("email@email.com")
			.encodedPassword("@@@@@@@@@@")
			.phone("010-1234-1344")
			.role(CUSTOMER)
			.build();
	}

	private LoginRequest getLoginRequest() {
		return new LoginRequest("email@email.com", "@@@@@@@@@@");
	}

	private LoginRequest getNotUserRequest() {
		return new LoginRequest("email2@email.com", "1232");
	}
}

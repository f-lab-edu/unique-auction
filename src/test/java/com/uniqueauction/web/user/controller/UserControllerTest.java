package com.uniqueauction.web.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueauction.AbstractContainerBaseTest;
import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.aop.LoginCheckAspect;
import com.uniqueauction.domain.user.service.UserService;
import com.uniqueauction.web.user.request.JoinRequest;
import com.uniqueauction.web.user.request.UpdateUserRequest;

@TestContainerBase
class UserControllerTest extends AbstractContainerBaseTest {

	private static final int USER_ID = 1;

	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;

	private MockHttpSession mockSession;
	private MockHttpServletRequest request;

	@MockBean
	LoginCheckAspect loginCheckAspect;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		mockSession = new MockHttpSession();
		mockSession.setAttribute("MEMBER_USER", 1);

		request = new MockHttpServletRequest();
		request.setSession(mockSession);

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		// request = new MockHttpServletRequest();
		// request.setSession(mockSession);
		// RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

	}

	@AfterEach
	void clean() {
		mockSession.clearAttributes();
	}

	@Test
	@DisplayName("유저 생성이 되면 status 200을 반환한다.")
	void userSaveTest() throws Exception {

		mockMvc.perform(
				post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(createUser())))
			.andExpect(status().isCreated());

	}

	// @Test
	@DisplayName("유저 수정 완료가 되면 status 200을 반환한다.")
	void userUpdateTest() throws Exception {

		mockMvc.perform(
				patch("/users/" + USER_ID)
					.session(mockSession)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(updateUser())))
			.andExpect(status().isOk());

	}

	private JoinRequest createUser() {
		return JoinRequest.builder()
			.email("1234@gmail.com")
			.phone("01012345678")
			.username("user1234")
			.password("aaRr3456j")
			.build();
	}

	private UpdateUserRequest updateUser() {
		return UpdateUserRequest.builder()
			.email("1234@gmail.com")
			.phone("01012345678")
			.username("user5678")
			.password("aaRr3456kk")
			.build();
	}
}

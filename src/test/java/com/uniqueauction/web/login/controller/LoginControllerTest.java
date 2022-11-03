package com.uniqueauction.web.login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueauction.AbstractContainerBaseTest;
import com.uniqueauction.TestContainerBase;
import com.uniqueauction.UniqueAuctionApplication;
import com.uniqueauction.exception.advice.CommonControllerAdvice;
import com.uniqueauction.web.login.request.LoginRequest;

/**
 * 로그인 서비스 테스트
 * MockMvc를 사용해 테스트용 시뮬레이션이 되도록 만들어 줌.
 * standaloneSetup : 해당 컨트롤러만 테스트 한다는 의미.
 * mvc.perform : MockMvc 실행
 * ObjectMapper : JSON 형식 사용시 응답 -> 직렬화   요청 ->역직렬화
 * 직렬화 Byte형태로 데이터 변환.
 * 역직렬화 : byte To Data Object
 * ex) String text = mapper.WriteValueAsString(car); //{"name":"K5","color":"gray"}
 * Car carObject = mapper.readValue(text, Car.class); //Car{name='k5',color='gary
 */
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@AutoConfigureMockMvc
@SpringBootTest(classes = UniqueAuctionApplication.class)
@TestContainerBase
class LoginControllerTest extends AbstractContainerBaseTest {

	private MockMvc mvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@SpyBean
	LoginController loginController;

	@BeforeEach
	public void setup() {
		mvc =
			MockMvcBuilders.standaloneSetup(loginController)
				.setControllerAdvice(new CommonControllerAdvice()) // 컨트롤 어드 바이스 추가.
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // utf-8 필터 추가
				.build();

	}

	@Test
	void emailFieldNullTest() throws Exception {
		LoginRequest req = new LoginRequest("", "12345678");

		final ResultActions actions =
			mvc.perform(
					post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isBadRequest());

	}

	// @Test
	void passwordFieldNullTest() throws Exception {

		LoginRequest req = new LoginRequest("email@email.com", "");

		final ResultActions actions =
			mvc.perform(
					post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isBadRequest());

	}

	@Test
	void passwordEightUnder() throws Exception {

		LoginRequest req = new LoginRequest("email@email.com", "123");

		final ResultActions actions =
			mvc.perform(
					post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isBadRequest());
	}

	/**
	 * 메서드가 리턴값이 있을시 given(loginService.login(any())).willThrow(LoginException.class);
	 * 메서드가 없을 시 doThrow 문법 사용
	 *
	 * @throws Exception
	 */
	@Test
	void notFoundUser() throws Exception {

		LoginRequest req = new LoginRequest("email@email.com", "12341aA234");

		mvc.perform(
				post("/login")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(req)))
			.andExpect(status().isNotFound());

	}
}

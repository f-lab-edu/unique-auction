package com.uniqueauction.web.login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.login.service.LoginService;
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
@SpringBootTest
@TestContainerBase
@AutoConfigureMockMvc
class LoginControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	LoginService loginService;

	ObjectMapper objectMapper = new ObjectMapper();

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

	@Test
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
}

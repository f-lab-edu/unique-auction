package com.uniqueauction.web.user.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueauction.AbstractContainerBaseTest;
import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.user.service.UserService;
import com.uniqueauction.web.user.request.JoinRequest;
import com.uniqueauction.web.user.request.UpdateUserRequest;

@TestContainerBase
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest extends AbstractContainerBaseTest {

	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

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

	@Test
	@DisplayName("유저 수정 완료가 되면 status 200을 반환한다.")
	void userUpdateTest() throws Exception {

		doReturn(updateUser().toEntity()).when(userService).update(updateUser());

		mockMvc.perform(
				patch("/users/" + 1)
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

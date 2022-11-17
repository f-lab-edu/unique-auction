package com.uniqueauction.web.user.controller;

import static com.uniqueauction.domain.user.entity.Role.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.service.UserService;
import com.uniqueauction.web.user.request.JoinRequest;
import com.uniqueauction.web.user.request.UpdateUserRequest;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	@DisplayName("유저 생성이 되면 status 200을 반환한다.")
	void userSaveTest() throws Exception {

		doReturn(getUser()).when(userService).join(any());

		mockMvc.perform(
				post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(createUser())))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("data.username", is("테스트유저")))
			.andExpect(jsonPath("data.email", is("email@email.com")))
			.andExpect(jsonPath("data.encodedPassword", is("@@@@@@@@@@")))
			.andExpect(jsonPath("data.phone", is("010-1234-1344")));
	}

	@Test
	@DisplayName("유저 수정 완료가 되면 status 200을 반환한다.")
	void userUpdateTest() throws Exception {

		doReturn(updateUser().toEntity()).when(userService).update(updateUser().toEntity());

		mockMvc.perform(
				patch("/users/" + 1)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(updateUser())))
			.andExpect(status().isOk());

	}

	private User getUser() {
		return User.builder()
			.username("테스트유저")
			.email("email@email.com")
			.encodedPassword("@@@@@@@@@@")
			.phone("010-1234-1344")
			.role(CUSTOMER)
			.build();
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

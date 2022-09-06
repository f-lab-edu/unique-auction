package com.uniqueAuction.web.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueAuction.domain.user.service.UserService;
import com.uniqueAuction.web.user.dto.JoinRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    /*
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void userJoinApiTest() throws Exception {
        JoinRequest joinRequest = JoinRequest.builder()
                .email("new@naver.com")
                .password("pw1234")
                .username("user1234")
                .phone("01012345678")
                .build();

        doNothing().when(userService).join(joinRequest.toUser());

        mockMvc.perform(post("/users")
                        .content(new ObjectMapper().writeValueAsString(joinRequest))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }
     */
}
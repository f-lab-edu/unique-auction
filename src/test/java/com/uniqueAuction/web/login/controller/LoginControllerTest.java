package com.uniqueAuction.web.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueAuction.domain.login.service.LoginService;
import com.uniqueAuction.exception.advice.login.LoginControllerAdvice;
import com.uniqueAuction.exception.advice.login.LoginException;
import com.uniqueAuction.web.login.request.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
class LoginControllerTest {

    private MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private LoginService loginService;

    @BeforeEach
    public void setup() {
        mvc =
                MockMvcBuilders.standaloneSetup(new LoginController(loginService))
                        .setControllerAdvice(new LoginControllerAdvice())
                        .addFilters(new CharacterEncodingFilter("UTF-8", true)) // utf-8 필터 추가
                        .build();
    }

    @Test
    void 이메일필드NullTest() throws Exception {


        LoginRequest req = new LoginRequest("", "12345678");

        final ResultActions actions =
                mvc.perform(
                                post("/login")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .characterEncoding("UTF-8")
                                        .content(objectMapper.writeValueAsString(req)))
                        .andExpect(status().isBadRequest());

        // then
        actions.andExpect(status().isBadRequest());
    }

    @Test
    void 비밀번호필드NullTest() throws Exception {

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
    void 비밀번호8자리미만테스트() throws Exception {

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


    @Test
    void 유저가없을시테스트() throws Exception {


        LoginRequest req = new LoginRequest("email@email.com", "12345678");
        given(loginService.login(any())).willThrow(LoginException.class);

        mvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8")
                                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isNotFound());

    }
}
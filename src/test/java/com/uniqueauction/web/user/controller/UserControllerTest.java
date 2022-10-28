package com.uniqueauction.web.user.controller;

import com.uniqueauction.TestContainerBase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestContainerBase
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

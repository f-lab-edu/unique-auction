package com.uniqueauction.web.trade.controller;

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
import com.uniqueauction.domain.trade.service.PurchaseService;
import com.uniqueauction.web.trade.request.PurchaseRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestContainerBase
class PurchaseControllerTest {

	private static final Long COMMON_ID = 1L;

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private PurchaseService purchaseService;

	@Test
	@DisplayName("구매입찰 정상 테스트 201 반환")
	void purchaseCreateTest() throws Exception {

		mockMvc.perform(
				post("/purchase")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(getPurchaseReq())))
			.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("리퀘스트 중 빈값이 있으면 예외를 반환한다.")
	void emptyTest() throws Exception {

		mockMvc.perform(
				post("/purchase")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(getEmptyPurchaseReq())))
			.andExpect(status().isBadRequest());
	}

	public PurchaseRequest getPurchaseReq() {
		return PurchaseRequest.builder()
			.userId(COMMON_ID)
			.productId(COMMON_ID)
			.productSize("256")
			.bidPrice("10000")
			.shippingAddress("test/est/test")
			.build();
	}

	public PurchaseRequest getEmptyPurchaseReq() {
		return PurchaseRequest.builder()
			.userId(COMMON_ID)
			.productId(COMMON_ID)
			.productSize("")
			.bidPrice("10000")
			.shippingAddress("test/est/test")
			.build();
	}
}

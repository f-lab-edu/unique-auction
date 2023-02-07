package com.uniqueauction.web.trade.controller;

import static com.uniqueauction.CommonUtilMethod.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
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
import com.uniqueauction.AbstractContainerBaseTest;
import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.trade.service.TradeService;
import com.uniqueauction.web.trade.request.TradeRequest;

@SpringBootTest
@TestContainerBase
@AutoConfigureMockMvc
class TradeControllerTest extends AbstractContainerBaseTest {

	private static final Long DUMY = getRandomLong();

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private TradeService tradeService;

	@Test
	@DisplayName("구매입찰 정상 테스트 201 반환")
	void purchaseCreateTest() throws Exception {

		doReturn(DUMY).when(tradeService).bidPurchase(any());

		mockMvc.perform(
				post("/purchase")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(getPurchaseRequest())))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("data", is(DUMY)));
	}

	@Test
	@DisplayName("리퀘스트 중 빈값이 있으면 예외를 반환한다.")
	void emptyTest() throws Exception {

		mockMvc.perform(
				post("/purchase")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(getEmptyPurchaseRequest())))
			.andExpect(status().isBadRequest());

	}

	public TradeRequest.SaveBidRequest getPurchaseRequest() {
		return TradeRequest.SaveBidRequest.builder()
			.userId(eq(getRandomLong()))
			.productId(getRandomLong())
			.productSize(getRandomString())
			.price(getRandomLong())
			.shippingAddress(getRandomString())
			.build();
	}

	public TradeRequest.SaveBidRequest getEmptyPurchaseRequest() {
		return TradeRequest.SaveBidRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.productSize("")
			.price(getRandomLong())
			.shippingAddress(getRandomString())
			.build();
	}

}

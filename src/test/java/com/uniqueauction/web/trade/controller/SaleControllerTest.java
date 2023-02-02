package com.uniqueauction.web.trade.controller;

import static com.uniqueauction.CommonUtilMethod.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
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
import com.uniqueauction.domain.trade.service.SaleService;
import com.uniqueauction.web.trade.request.SaleRequest;

@SpringBootTest
@TestContainerBase
@AutoConfigureMockMvc
class SaleControllerTest extends AbstractContainerBaseTest {

	private static final Long DUMY = getRandomLong();

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private SaleService saleService;

	@Test
	@DisplayName("판매입찰 정상 테스트 201 반환 ")
	void saleCreateTest() throws Exception {

		doReturn(DUMY).when(saleService).saveSale(any());

		mockMvc.perform(
				post("/sale")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(getSaleReq())))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("data", is(DUMY)));

	}

	@Test
	@DisplayName("리퀘스트 중 빈값이 있으면 예외를 반환한다.")
	void emptyTest() throws Exception {

		mockMvc.perform(
				post("/sale")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(getEmptySaleReq())))
			.andExpect(status().isBadRequest());
	}

	public SaleRequest getSaleReq() {
		return SaleRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.productSize("256")
			.bidPrice("10000")
			.returnAddress("test/est/test")
			.bidDueDate("20230205")
			.build();
	}

	public SaleRequest getEmptySaleReq() {
		return SaleRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.productSize("")
			.bidPrice("10000")
			.returnAddress("test/est/test")
			.bidDueDate("20230205")
			.build();
	}

}

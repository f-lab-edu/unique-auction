package com.uniqueauction.web.product.controller;

import static com.uniqueauction.CommonUtilMethod.*;
import static com.uniqueauction.domain.product.entity.Category.*;
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
import com.uniqueauction.domain.product.service.ProductService;
import com.uniqueauction.web.product.request.ProductSaveRequest;
import com.uniqueauction.web.product.request.ProductUpdateRequest;

@SpringBootTest
@TestContainerBase
@AutoConfigureMockMvc
class ProductControllerTest extends AbstractContainerBaseTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private ProductService productService;

	@Test
	@DisplayName("상품 저장 완료가 되면 status  200을 반환한다.")
	void productSaveTest() throws Exception {

		doReturn(createProduct().toEntity()).when(productService).save(any());

		mockMvc.perform(

				post("/products")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(createProduct())))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("data.name", is("상품1")))
			.andExpect(jsonPath("data.modelNumber", is("1")))
			.andExpect(jsonPath("data.releasePrice", is("10000")))
			.andExpect(jsonPath("data.category", is("SHOES")))
			.andExpect(jsonPath("data.imgUrl", is("/test/test")))
			.andExpect(jsonPath("data.brand", is("DD")));

	}

	@Test
	@DisplayName("상품 수정  완료가 되면 status  200을 반환한다.")
	void productUpdateTest() throws Exception {

		doReturn(updateProduct().toEntity()).when(productService).update(updateProduct().toEntity());

		mockMvc.perform(
				patch("/products/" + 1)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(updateProduct())))
			.andExpect(status().isOk());

	}

	@Test
	@DisplayName("상품 삭제  완료가 되면 status  200을 반환한다.")
	void productDeleteTest() throws Exception {

		mockMvc.perform(
				delete("/products/" + 1)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(String.valueOf(getRandomLong())))
			.andExpect(status().isOk());

	}

	@Test
	@DisplayName("상품 상세  완료가 되면 status  200을 반환한다.")
	void productDetailSelectTest() throws Exception {

		mockMvc.perform(
				get("/products/" + 1)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(String.valueOf(getRandomLong())))
			.andExpect(status().isOk());

	}

	@Test
	@DisplayName("필드가 비어 있으면 예외를 반환한다")
	void fieldNullCheck() throws Exception {

		mockMvc.perform(
				post("/products")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(createNullField())))
			.andExpect(status().isBadRequest());
	}

	private ProductSaveRequest createProduct() {
		return ProductSaveRequest.builder()
			.productName("상품1")
			.modelNumber("1")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl("/test/test")
			.brand("DD")
			.build();
	}

	private ProductSaveRequest createNullField() {
		return ProductSaveRequest.builder()
			.productName("상품2")
			.modelNumber("")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl("test/test")
			.brand("DD")
			.imgUrl("/test/test")
			.build();
	}

	private ProductUpdateRequest updateProduct() {
		return ProductUpdateRequest.builder()
			.productId(getRandomLong())
			.productName("상품2")
			.modelNumber("1")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl(":test")
			.brand("DD")
			.imgUrl("/test/test")
			.build();
	}
}

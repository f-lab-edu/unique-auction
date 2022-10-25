package com.uniqueauction.web.product.controller;

import static com.uniqueauction.domain.product.entity.Category.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.product.service.ProductService;
import com.uniqueauction.exception.advice.CommonControllerAdvice;
import com.uniqueauction.web.product.request.ProductSaveRequest;
import com.uniqueauction.web.product.request.ProductUpdateRequest;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	ProductController productController;

	@MockBean
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	private ProductSaveRequest createProduct() {
		return ProductSaveRequest.builder()
			.productName("상품1")
			.modelNumber("1")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl(":test")
			.brand("DD")
			.build();
	}

	private ProductSaveRequest createNullField() {
		return ProductSaveRequest.builder()
			.productName("상품2")
			.modelNumber("")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl(":test")
			.brand("DD")
			.imgUrl("/test/test")
			.build();
	}

	private ProductUpdateRequest updateProduct() {
		return ProductUpdateRequest.builder()
			.productId(1L)
			.productName("상품2")
			.modelNumber("1")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl(":test")
			.brand("DD")
			.imgUrl("/test/test")
			.build();
	}

	@BeforeEach
	public void setup() {
		productController = new ProductController(productService, productRepository);
		mockMvc =
			MockMvcBuilders.standaloneSetup(productController)
				.setControllerAdvice(new CommonControllerAdvice())
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.build();
	}

	@Test
	@DisplayName("상품 저장 완료가 되면 status  200을 반환한다.")
	void productSaveTest() throws Exception {

		mockMvc.perform(

				post("/products")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(createProduct())))
			.andExpect(status().isOk());

		verify(productService).save(any(Product.class));

	}

	@Test
	@DisplayName("상품 수정  완료가 되면 status  200을 반환한다.")
	void productUpdateTest() throws Exception {

		mockMvc.perform(
				patch("/products/" + 1)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(updateProduct())))
			.andExpect(status().isOk());

		verify(productService).update(any(Product.class));

	}

	@Test
	@DisplayName("상품 삭제  완료가 되면 status  200을 반환한다.")
	void productDeleteTest() throws Exception {
		Long id = 1L;

		mockMvc.perform(
				delete("/products/" + 1)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(String.valueOf(id)))
			.andExpect(status().isOk());

		verify(productService).delete(any(Long.class));

	}

	@Test
	@DisplayName("상품 상세  완료가 되면 status  200을 반환한다.")
	void productDetailSelectTest() throws Exception {
		Long id = 1L;

		mockMvc.perform(
				get("/products/" + 1)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(String.valueOf(id)))
			.andExpect(status().isOk());

		verify(productRepository).findById(any(Long.class));

	}

	@Disabled
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

}

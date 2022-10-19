package com.uniqueauction.web.product.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {
	//
	// private MockMvc mockMvc;
	//
	// ObjectMapper objectMapper = new ObjectMapper();
	//
	// @MockBean
	// private ProductService productService;
	// @MockBean
	// private ProductRepository productRepository;
	//
	// private ProductSaveRequest saveReq;
	// private ProductUpdateRequest updateReq;
	//
	// @BeforeEach
	// public void setup() {
	// 	mockMvc =
	// 		MockMvcBuilders.standaloneSetup(new ProductController(productService, productRepository))
	// 			.setControllerAdvice(new CommonControllerAdvice())
	// 			.addFilters(new CharacterEncodingFilter("UTF-8", true))
	// 			.build();
	//
	// 	saveReq = new ProductSaveRequest("123", "10000", "284", "운동화", "1");
	// 	updateReq = new ProductUpdateRequest("456", "10000", "284", "운동화", "12");
	//
	// }
	//
	// @Test
	// @DisplayName("상품 저장 완료가 되면 status  200을 반환한다.")
	// void productSaveTest() throws Exception {
	//
	// 	mockMvc.perform(
	//
	// 			post("/products")
	// 				.contentType(MediaType.APPLICATION_JSON)
	// 				.accept(MediaType.APPLICATION_JSON)
	// 				.characterEncoding("UTF-8")
	// 				.content(objectMapper.writeValueAsString(saveReq)))
	// 		.andExpect(status().isOk());
	// }
	//
	// @Test
	// void productUpdateTest() throws Exception {
	//
	// 	//        when(productService.updateProduct(1L, updateRequest)).thenReturn(updateRequest.updateProduct());
	//
	// 	mockMvc.perform(
	// 			patch("/products/" + 1)
	// 				.contentType(MediaType.APPLICATION_JSON)
	// 				.accept(MediaType.APPLICATION_JSON)
	// 				.characterEncoding("UTF-8")
	// 				.content(objectMapper.writeValueAsString(updateReq)))
	// 		.andExpect(status().isOk());
	// }
	//
	// @Test
	// void productDeleteTest() throws Exception {
	// 	Long id = 1L;
	//
	// 	mockMvc.perform(
	// 			delete("/products/" + 1)
	// 				.contentType(MediaType.APPLICATION_JSON)
	// 				.accept(MediaType.APPLICATION_JSON)
	// 				.characterEncoding("UTF-8")
	// 				.content(String.valueOf(id)))
	// 		.andExpect(status().isOk());
	// }
	//
	// @Test
	// void productfindTest() throws Exception {
	// 	Long id = 1L;
	//
	// 	mockMvc.perform(
	// 			get("/products/" + 1)
	// 				.contentType(MediaType.APPLICATION_JSON)
	// 				.accept(MediaType.APPLICATION_JSON)
	// 				.characterEncoding("UTF-8")
	// 				.content(String.valueOf(id)))
	// 		.andExpect(status().isOk());
	// }

}

package com.uniqueauction.web.review.controller;

import static com.uniqueauction.CommonUtilMethod.*;
import static com.uniqueauction.domain.product.entity.Category.*;
import static com.uniqueauction.domain.user.entity.Role.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.service.ProductService;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.review.service.ReviewService;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.service.UserService;
import com.uniqueauction.web.review.request.SaveReviewRequest;
import com.uniqueauction.web.review.response.ReviewByProductResponse;
import com.uniqueauction.web.review.response.ReviewByUserResponse;
import com.uniqueauction.web.review.response.ReviewInfo;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	private ReviewService reviewService;

	@MockBean
	private UserService userService;

	@MockBean
	private ProductService productService;

	@Test
	@DisplayName("?????? ?????? ????????? ?????? status  201??? ????????????.")
	void reviewSaveTest() throws Exception {

		doReturn(getUser()).when(userService).findById(anyLong());
		doReturn(getProduct()).when(productService).findById(anyLong());
		doReturn(getReview()).when(reviewService).save(any(Review.class));

		mockMvc.perform(
				post("/reviews")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(createSaveReviewsReq())))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("data.score", is(5)))
			.andExpect(jsonPath("data.content", is("test")));

	}

	@Test
	@DisplayName("?????? ????????? 5??? ???????????? 400 ?????? ???????????????.")
	void reviewScoreOverFive() throws Exception {

		mockMvc.perform(
				post("/reviews")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(overFiveScoreSaveReviewsReq())))
			.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("?????? ????????? 1 ?????? 400 ?????? ???????????????.")
	void reviewScoreUnderOne() throws Exception {

		mockMvc.perform(
				post("/reviews")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(underOneScoreSaveReviewsReq())))
			.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("???????????? ID??? ?????? ???????????? ????????????.")
	void selectProductReviws() throws Exception {

		doReturn(getProduct()).when(productService).findById(anyLong());

		doReturn(findByPidRes()).when(reviewService).findByProductId(any(Product.class));

		mockMvc.perform(get("/reviews/1/products")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(handler().handlerType(ReviewController.class))
			.andExpect(handler().methodName("selectProductReviews"))
			.andExpect(jsonPath("data.productName", is("??????1")))
			.andExpect(jsonPath("data.category", is("SHOES")))
			.andExpect(jsonPath("data.brand", is("NIKE")))
			.andExpect(jsonPath("data.imgUrl", is("test")))
			.andExpect(jsonPath("data.reviews.length()", is(1)))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@DisplayName("?????? ID??? ?????? ???????????? ????????????.")
	void selectUserReviws() throws Exception {
		doReturn(getUser()).when(userService).findById(anyLong());
		doReturn(findByUidRes()).when(reviewService).findByUserId(any(User.class));

		mockMvc.perform(get("/reviews/1/users")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(handler().handlerType(ReviewController.class))
			.andExpect(handler().methodName("selectUserProductReviews"))
			.andExpect(jsonPath("data.email", is("test@test.com")))
			.andExpect(jsonPath("data.username", is("test")))
			.andExpect(jsonPath("data.reviews.length()", is(1)))
			.andDo(MockMvcResultHandlers.print());
	}

	private SaveReviewRequest createSaveReviewsReq() {
		return SaveReviewRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.score(3)
			.content("test")
			.build();
	}

	private SaveReviewRequest overFiveScoreSaveReviewsReq() {
		return SaveReviewRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.score(6)
			.content("test")
			.build();
	}

	private SaveReviewRequest underOneScoreSaveReviewsReq() {
		return SaveReviewRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.score(0)
			.content("test")
			.build();
	}

	private ReviewByProductResponse findByPidRes() {
		return ReviewByProductResponse.builder()
			.brand("NIKE")
			.category(SHOES)
			.imgUrl("test")
			.productName("??????1")
			.reviews(getReviewInfos())
			.build();
	}

	private ReviewByUserResponse findByUidRes() {
		return ReviewByUserResponse.builder()
			.email("test@test.com")
			.username("test")
			.reviews(getReviewInfos())
			.build();
	}

	private Review getReview() {
		return Review.builder()
			.content("test")
			.score(5)
			.user(getUser())
			.product(getProduct())
			.build();

	}

	private User getUser() {
		return User.builder()
			.id(getRandomLong())
			.email("test@test.com")
			.username("test")
			.encodedPassword("1234Aa1234")
			.phone("010-1234-1234")
			.role(CUSTOMER)
			.build();
	}

	private Product getProduct() {
		return Product.builder()
			.id(getRandomLong())
			.name("??????1")
			.modelNumber("1234")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl("/test/set")
			.brand("NIKE")
			.build();
	}

	@NotNull
	private List<ReviewInfo> getReviewInfos() {
		List<ReviewInfo> reviewList = new ArrayList<>();

		reviewList.add(new ReviewInfo(5, "test"));
		return reviewList;
	}

}

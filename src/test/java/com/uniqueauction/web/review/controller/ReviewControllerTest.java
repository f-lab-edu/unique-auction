package com.uniqueauction.web.review.controller;

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
import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.review.service.ReviewService;
import com.uniqueauction.domain.user.entity.User;
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

	@Test
	@DisplayName("리뷰 저장 완료가 되면 status  201을 반환한다.")
	void reviewSaveTest() throws Exception {

		doReturn(getReview()).when(reviewService).save(any(SaveReviewRequest.class));

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
	@DisplayName("리뷰 점수가 5가 초과하면 400 에러 발생시킨다.")
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
	@DisplayName("리뷰 점수가 1 미만 400 에러 발생시킨다.")
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
	@DisplayName("프로덕트 ID로 리뷰 목록들을 조회한다.")
	void selectProductReviws() throws Exception {

		doReturn(findByPidRes()).when(reviewService).findByProductId(1L);

		mockMvc.perform(get("/reviews/1/products")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(handler().handlerType(ReviewController.class))
			.andExpect(handler().methodName("selectProductReviews"))
			.andExpect(jsonPath("data.productName", is("신발1")))
			.andExpect(jsonPath("data.category", is("SHOES")))
			.andExpect(jsonPath("data.brand", is("NIKE")))
			.andExpect(jsonPath("data.imgUrl", is("test")))
			.andExpect(jsonPath("data.reviews.length()", is(1)))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@DisplayName("유저 ID로 리뷰 목록들을 조회한다.")
	void selectUserReviws() throws Exception {

		doReturn(findByUidRes()).when(reviewService).findByUserId(1L);

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
			.userId(1L)
			.productId(1L)
			.score(3)
			.content("test")
			.build();
	}

	private SaveReviewRequest overFiveScoreSaveReviewsReq() {
		return SaveReviewRequest.builder()
			.userId(1L)
			.productId(1L)
			.score(6)
			.content("test")
			.build();
	}

	private SaveReviewRequest underOneScoreSaveReviewsReq() {
		return SaveReviewRequest.builder()
			.userId(1L)
			.productId(1L)
			.score(0)
			.content("test")
			.build();
	}

	private ReviewByProductResponse findByPidRes() {
		return ReviewByProductResponse.builder()
			.brand("NIKE")
			.category(SHOES)
			.imgUrl("test")
			.productName("신발1")
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
			.id(1L)
			.email("test@test.com")
			.username("test")
			.encodedPassword("1234Aa1234")
			.phone("010-1234-1234")
			.role(CUSTOMER)
			.build();
	}

	private Product getProduct() {
		return Product.builder()
			.id(1L)
			.name("상품1")
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

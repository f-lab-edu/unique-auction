package com.uniqueauction.domain.review.service;

import static com.uniqueauction.domain.product.entity.Category.*;
import static com.uniqueauction.domain.user.entity.Role.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.review.repository.ReviewRepository;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.event.ReviewEventPublisher;
import com.uniqueauction.exception.advice.CommonNotFoundException;
import com.uniqueauction.web.review.request.SaveReviewRequest;
import com.uniqueauction.web.review.response.ReviewByProductResponse;
import com.uniqueauction.web.review.response.ReviewByUserResponse;
import com.uniqueauction.web.review.response.ReviewInfo;

// @TestContainerBase
@ExtendWith(MockitoExtension.class) // 클래스단에 해당 어노테이션을 달아, 클래스가 Mockito를 사용함을 명시적으로 알립니다.
class ReviewServiceTest {

	private static final Long commonId = 1L;

	@InjectMocks
	ReviewService reviewService;

	@Mock
	ReviewRepository reviewRepository;

	@Mock
	ReviewEventPublisher reviewEventPublisher;

	@BeforeEach
	void setUp() {
		reviewService = new ReviewService(reviewRepository, reviewEventPublisher);
	}

	@Test
	@DisplayName("리뷰 저장 테스트")
	void saveReviewsTest() {
		//given

		doReturn(getUser()).when(reviewEventPublisher).getUser(any(Long.class));
		doReturn(getProduct()).when(reviewEventPublisher).getProduct(any(Long.class));
		doReturn(getReview()).when(reviewRepository).save(any(Review.class));

		// doReturn(getReview()).when(reviewRepository).save(getReview()); 왜 null이 나올까?

		//when
		Review save = reviewService.save(createSaveReviewsReq());

		assertThat(getProduct()).isEqualTo(save.getProduct());
		assertThat(getUser()).isEqualTo(save.getUser());
		assertThat("test").isEqualTo(save.getContent());
		assertThat(5).isEqualTo(save.getScore());
	}

	@Test
	@DisplayName("프로덕트 아이디로 리뷰 조회")
	void findByProductId() {
		doReturn(getReviewsInfo()).when(reviewRepository).findByProductId(any(Long.class));
		doReturn(getProduct()).when(reviewEventPublisher).getProduct(any(Long.class));

		//when
		ReviewByProductResponse byProductId = reviewService.findByProductId(any(Long.class));
		//then

		assertThat(byProductId.getProductName()).isEqualTo("상품1");
		assertThat(byProductId.getBrand()).isEqualTo("NIKE");
		assertThat(byProductId.getReviews().size()).isEqualTo(5);
	}

	@Test
	@DisplayName("User 아이디로 리뷰 조회")
	void findByUserId() {

		doReturn(getReviewsInfo()).when(reviewRepository).findByUserId(any(Long.class));
		doReturn(getUser()).when(reviewEventPublisher).getUser(any(Long.class));

		//when
		ReviewByUserResponse byUserId = reviewService.findByUserId(any(Long.class));
		//then

		assertThat(byUserId.getUsername()).isEqualTo("test");
		assertThat(byUserId.getEmail()).isEqualTo("test@test.com");
		assertThat(byUserId.getReviews().size()).isEqualTo(5);
	}

	@Test
	@DisplayName("상품이 없을땐 NOTFOUND EXCEPTION")
	void emptyProductNotFoundTest() {

		//when
		doThrow(CommonNotFoundException.class).when(reviewEventPublisher).getProduct(any(Long.class));
		//then

		assertThatThrownBy(
			() -> reviewService.save(createSaveReviewsReq())
		).isInstanceOf(CommonNotFoundException.class);

	}

	@Test
	@DisplayName("유저이 없을땐 NOTFOUND EXCEPTION")
	void emptyUserNotFoundTest() {

		//when
		doThrow(CommonNotFoundException.class).when(reviewEventPublisher).getUser(any(Long.class));
		//then

		assertThatThrownBy(
			() -> reviewService.save(createSaveReviewsReq())
		).isInstanceOf(CommonNotFoundException.class);

	}

	private SaveReviewRequest createSaveReviewsReq() {
		return SaveReviewRequest.builder()
			.userId(1L)
			.productId(1L)
			.score(3)
			.content("test")
			.build();
	}

	private User getUser() {
		return User.builder()
			.id(commonId)
			.email("test@test.com")
			.username("test")
			.encodedPassword("1234Aa1234")
			.phone("010-1234-1234")
			.role(CUSTOMER)
			.build();
	}

	private Product getProduct() {
		return Product.builder()
			.id(commonId)
			.name("상품1")
			.modelNumber("1234")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl("/test/set")
			.brand("NIKE")
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

	private List<ReviewInfo> getReviewsInfo() {
		List<ReviewInfo> reviews = new ArrayList<>();

		reviews.add(new ReviewInfo(5, "test1"));
		reviews.add(new ReviewInfo(4, "test2"));
		reviews.add(new ReviewInfo(3, "test3"));
		reviews.add(new ReviewInfo(2, "test4"));
		reviews.add(new ReviewInfo(1, "test5"));

		return reviews;
	}

}
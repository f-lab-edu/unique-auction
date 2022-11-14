package com.uniqueauction.domain.review.repository;

import static com.uniqueauction.domain.product.entity.Category.*;
import static com.uniqueauction.domain.user.entity.Role.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.web.review.request.SaveReviewRequest;
import com.uniqueauction.web.review.response.ReviewInfo;

@TestContainerBase
class ReviewRepositoryTest {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	Review review;

	User user;

	Product product;

	@BeforeEach
	void set() {
		System.out.println("setUp");
		user = userRepository.save(getUser());
		product = productRepository.save(getProduct());
		review = repository.save(Review.createReview(user, product, createSaveReviewsReq()));
	}

	@AfterEach
	void clear() {
		System.out.println("clear");
		repository.deleteAll();
		userRepository.deleteAll();
		productRepository.deleteAll();
	}

	@Test
	@Order(1)
	@DisplayName("리뷰 저장 테스트")
	void reviewSave() {

		//then
		assertThat(review.getContent()).isEqualTo("test");
	}

	@Test
	@Order(2)
	@DisplayName("상품 아이디로 조회 테스트")
	void findByProductIdTest() {
		//given
		Review review = repository.findAll().get(0);
		//when
		ReviewInfo byProductId = repository.findByProductId(review.getProduct().getId()).get(0);

		//then
		assertThat(byProductId.getContent()).isEqualTo("test");
	}

	@Test
	@Order(3)
	@DisplayName("유저 아이디로 조회 테스트")
	void findByUserIdTest() {

		//given
		Review review = repository.findAll().get(0);

		//when
		ReviewInfo byUserId = repository.findByUserId(review.getUser().getId()).get(0);

		//then
		assertThat(byUserId.getContent()).isEqualTo("test");
	}

	private User getUser() {
		return User.builder()
			// .id(commonId)
			.email("test@test.com")
			.username("test")
			.encodedPassword("1234Aa1234")
			.phone("010-1234-1234")
			.role(CUSTOMER)
			.build();
	}

	private Product getProduct() {
		return Product.builder()
			// .id(commonId)
			.name("상품1")
			.modelNumber("1234")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl("/test/set")
			.brand("NIKE")
			.build();
	}

	private SaveReviewRequest createSaveReviewsReq() {
		return SaveReviewRequest.builder()
			.userId(1L)
			.productId(1L)
			.score(3)
			.content("test")
			.build();
	}
}

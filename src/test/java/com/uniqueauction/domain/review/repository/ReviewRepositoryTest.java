package com.uniqueauction.domain.review.repository;


import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.review.entity.Review;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.web.review.request.SaveReviewRequest;
import com.uniqueauction.web.review.response.ReviewInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uniqueauction.CommonUtilMethod.getRandomLong;
import static com.uniqueauction.domain.product.entity.Category.SHOES;
import static com.uniqueauction.domain.user.entity.Role.CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;

@TestContainerBase
@SpringBootTest
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

	@Test
	@DisplayName("리뷰 저장 테스트")
	void reviewSave() {
		//then
		assertThat(review.getContent()).isEqualTo("test");
		clear();
	}

	@Test
	@DisplayName("상품 아이디로 조회 테스트")
	void findByProductIdTest() {
		//when
		ReviewInfo byProductId = repository.findByProductId(review.getProduct().getId()).get(0);
		//then
		assertThat(byProductId.getContent()).isEqualTo("test");
		clear();
	}

	@Test
	@DisplayName("유저 아이디로 조회 테스트")
	void findByUserIdTest() {
		//when
		ReviewInfo byUserId = repository.findByUserId(review.getUser().getId()).get(0);
		//then
		assertThat(byUserId.getContent()).isEqualTo("test");
		clear();
	}

	private User getUser() {
		return User.builder()
			.email("test@test.com")
			.username("test")
			.encodedPassword("1234Aa1234")
			.phone("010-1234-1234")
			.role(CUSTOMER)
			.build();
	}

	private Product getProduct() {
		return Product.builder()
			.name("상품1")
			.modelNumber("1234")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl("/test/set")
			.brand("NIKE")
			.build();
	}

	private SaveReviewRequest createSaveReviewsRequest() {
		return SaveReviewRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.score(3)
			.content("test")
			.build();
	}
	@BeforeEach
	private void setUp() {
		user = userRepository.save(getUser());
		product = productRepository.save(getProduct());
		review = repository.save(Review.createReview(user, product, createSaveReviewsRequest()));
	}

	private void clear() {
		repository.delete(review);
		userRepository.delete(user);
		productRepository.delete(product);
	}

}

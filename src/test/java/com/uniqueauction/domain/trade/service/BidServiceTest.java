package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.CommonUtilMethod.*;
import static com.uniqueauction.domain.product.entity.Category.*;
import static com.uniqueauction.exception.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.redisson.api.RLock;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;
import com.uniqueauction.domain.trade.repository.TradeRepository;
import com.uniqueauction.domain.user.entity.Role;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.infrastructure.kafka.KafkaProducer;
import com.uniqueauction.infrastructure.redis.RedisLockRepository;
import com.uniqueauction.web.trade.request.TradeRequest;

class BidServiceTest {
	@InjectMocks
	private BidService bidService;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private TradeRepository tradeRepository;

	@Mock
	private KafkaProducer kafkaProducer;

	@Mock
	private RedisLockRepository redisLockRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		bidService = new BidService(productRepository, tradeRepository, userRepository, kafkaProducer,
			redisLockRepository);
	}

	@Test
	@DisplayName("거래 요청 성공 테스트")
	void createBidTest_Success() {
		User bidder = getUser();
		Product product = getProduct();
		TradeRequest tradeRequest = getTradeRequest();
		RLock lock = redisLockRepository.getLock("trade_" + product.getId());

		// mock 설정
		when(userRepository.findById(tradeRequest.getUserId())).thenReturn(Optional.of(bidder));
		when(productRepository.findById(tradeRequest.getProductId())).thenReturn(Optional.of(product));
		when(tradeRepository.findByPublisherIdAndProductIdAndProductSizeAndTradeStatus(
			bidder.getId(), product.getId(), tradeRequest.getProductSize(), tradeRequest.getTradeStatus()))
			.thenReturn(Optional.empty());
		when(tradeRepository.save(mock(Trade.class))).thenReturn(
			tradeRequest.convert(product.getId(), tradeRequest.getTradeStatus()));
		when(redisLockRepository.tryLock(lock, 5, 60)).thenReturn(true);

		// 테스트
		bidService.createBid(tradeRequest);

		// 검증
		assertThat(tradeRequest.getPrice()).isEqualTo(tradeRequest.getPrice());
		assertThat(tradeRequest.getShippingAddress()).isEqualTo(tradeRequest.getShippingAddress());
	}

	@Test
	@DisplayName("거래 요청 실패: 존재하지 않는 user ID")
	void createBidTest_Fail_NotFoundUser() {
		// given
		TradeRequest tradeRequest = getTradeRequest();
		when(userRepository.findById(tradeRequest.getUserId())).thenReturn(Optional.empty());

		// when & then
		assertThrows(CommonException.class, () -> bidService.createBid(tradeRequest), NOT_FOUND_USER.getMessage());
	}

	@Test
	@DisplayName("거래 요청 실패: 존재하지 않는 product ID")
	void createBidTest_Fail_NotFoundProduct() {
		// given
		TradeRequest tradeRequest = getTradeRequest();
		when(userRepository.findById(tradeRequest.getUserId())).thenReturn(Optional.of(getUser()));
		when(productRepository.findById(tradeRequest.getProductId())).thenReturn(Optional.empty());

		// when & then
		assertThrows(CommonException.class, () -> bidService.createBid(tradeRequest), NOT_FOUND_PRODUCT.getMessage());
	}

	private TradeRequest getTradeRequest() {
		return TradeRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.productSize("275")
			.price(130000L)
			.shippingAddress("test/est/test")
			.tradeStatus(TradeStatus.PURCHASE_PROGRESS)
			.build();
	}

	private Product getProduct() {
		return Product.builder()
			.id(getRandomLong())
			.name("New Balance 878 Triple Black")
			.modelNumber("CM878XL")
			.releasePrice("118000")
			.category(SHOES)
			.imgUrl("/test/set")
			.brand("New Balance")
			.build();
	}

	private User getUser() {
		return User.builder()
			.id(getRandomLong())
			.email("test_user@gmail.com")
			.encodedPassword("user1234!!")
			.username("테스트유저")
			.phone("01012341234")
			.role(Role.CUSTOMER)
			.build();
	}

}

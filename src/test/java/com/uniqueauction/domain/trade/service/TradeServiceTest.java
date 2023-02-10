package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.CommonUtilMethod.*;
import static com.uniqueauction.domain.product.entity.Category.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.repository.TradeRepository;
import com.uniqueauction.domain.user.entity.Role;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.web.trade.request.TradeRequest;

@ExtendWith(MockitoExtension.class) // 클래스단에 해당 어노테이션을 달아, 클래스가 Mockito를 사용함을 명시적으로 알립니다.
class TradeServiceTest {

	@InjectMocks
	TradeService tradeService;

	@Mock
	ProductRepository productRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	TradeRepository tradeRepository;

	@BeforeEach
	void setUp() {
		tradeService = new TradeService(productRepository, tradeRepository, userRepository);
	}

	@Test
	@DisplayName("구매 등록 테스트")
	void savePurchaseTest() {
		User user = getUser();
		Product product = getProduct();
		String address = "서울 용산구 대사관로 110";

		//given
		doReturn(Optional.ofNullable(getProduct())).when(productRepository).findById(any(Long.class));
		doReturn(Optional.ofNullable(getUser())).when(userRepository).findById(any(Long.class));
		Trade trade = getTradeRequest().convertForBuyer(product.getId());
		doReturn(trade).when(tradeRepository).save(any(Trade.class));

		//when
		tradeService.createPurchase(getTradeRequest());

		//then
		verify(productRepository).findById(any(Long.class));
		verify(tradeRepository).save(any(Trade.class));
	}

	@Test
	@DisplayName("구매입찰 등록 실패테스트(물품이없을때)")
	void savePurchaseFailNotFoundProductTest() {
		//given
		//doThrow(CommonException.class).when(productRepository).findById(any(Long.class));
		//then
		assertThatThrownBy(
			() ->
				tradeService.createPurchase(getTradeRequest())
		).isInstanceOf(CommonException.class);

	}

	public TradeRequest getTradeRequest() {
		return TradeRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.productSize("275")
			.price(130000L)
			.shippingAddress("test/est/test")
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

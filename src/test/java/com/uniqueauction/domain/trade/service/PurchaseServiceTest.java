package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.CommonUtilMethod.*;
import static com.uniqueauction.domain.product.entity.Category.*;
import static com.uniqueauction.domain.trade.entity.TradeStatus.*;
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
import com.uniqueauction.domain.trade.entity.Purchase;
import com.uniqueauction.domain.trade.entity.Sale;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;
import com.uniqueauction.domain.trade.repository.PurchaseRepository;
import com.uniqueauction.domain.trade.repository.SaleRepository;
import com.uniqueauction.domain.trade.repository.TradeRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.web.trade.request.PurchaseRequest;

@ExtendWith(MockitoExtension.class) // 클래스단에 해당 어노테이션을 달아, 클래스가 Mockito를 사용함을 명시적으로 알립니다.
class PurchaseServiceTest {

	@InjectMocks
	PurchaseService purchaseService;

	@Mock
	ProductRepository productRepository;

	@Mock
	PurchaseRepository purchaseRepository;

	@Mock
	SaleRepository saleRepository;

	@Mock
	TradeRepository tradeRepository;

	@BeforeEach
	void setUp() {
		purchaseService = new PurchaseService(productRepository, purchaseRepository, saleRepository, tradeRepository);
	}

	@Test
	@DisplayName("구매입찰 등록 테스트")
	void savePurchaseTest() {
		//given

		doReturn(Optional.ofNullable(getProduct())).when(productRepository).findById(any(Long.class));
		Purchase purchase = getPurchaseReq().toEntity();
		purchase.setProduct(getProduct());

		doReturn(false)
			.when(purchaseRepository)
			.existsByProductAndProductSize(any(Product.class), any(String.class));

		doReturn(purchase).when(purchaseRepository).save(any(Purchase.class));

		doReturn(Optional.ofNullable(getSale())).when(saleRepository)
			.findByProductAndProductSize(any(Product.class), any(String.class));

		doReturn(getTrade(purchase)).when(tradeRepository).save(any(Trade.class));

		//when

		purchaseService.savePurchase(getPurchaseReq());

		//then

		verify(productRepository).findById(any(Long.class));
		verify(purchaseRepository).existsByProductAndProductSize(any(Product.class), any(String.class));
		verify(purchaseRepository).save(any(Purchase.class));
		verify(saleRepository).findByProductAndProductSize(any(Product.class), any(String.class));
		verify(tradeRepository).save(any(Trade.class));

	}

	@Test
	@DisplayName("구매입찰 등록 실패테스트(물품이없을때)")
	void savePurchaseFailNotFoundProductTest() {
		//given

		doThrow(CommonException.class).when(productRepository).findById(any(Long.class));

		//when
		//then
		assertThatThrownBy(
			() ->
				purchaseService.savePurchase(getPurchaseReq())
		).isInstanceOf(CommonException.class);

	}

	@Test
	@DisplayName("구매입찰 등록 실패테스트(중복된 구매물품일때 )")
	void savePurchaseFailDuplicatePurchaseTest() {
		//given

		//when
		doThrow(CommonException.class).when(productRepository).findById(any(Long.class));

		//then
		assertThatThrownBy(
			() ->
				purchaseService.savePurchase(getPurchaseReq())
		).isInstanceOf(CommonException.class);

	}

	public PurchaseRequest getPurchaseReq() {
		return PurchaseRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.productSize("256")
			.bidPrice("10000")
			.shippingAddress("test/est/test")
			.build();
	}

	private Product getProduct() {
		return Product.builder()
			.id(getRandomLong())
			.name("상품1")
			.modelNumber("1234")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl("/test/set")
			.brand("NIKE")
			.build();
	}

	private Sale getSale() {
		return Sale.builder()
			.id(getRandomLong())
			.userId(getRandomLong())
			.productSize("25666")
			.bidPrice("10000")
			.returnAddress("TESTSETTETETT")
			.tradeStatus(BID_PROGRESS)
			.build();
	}

	private Trade getTrade(Purchase purchase) {
		Trade trade = Trade.builder().purchase(purchase)
			.sale(getSale())
			.status(TradeStatus.BID_COMPLETE)
			.build();
		return trade;
	}

}

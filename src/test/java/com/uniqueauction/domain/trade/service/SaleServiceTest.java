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
import com.uniqueauction.web.trade.request.SaleRequest;

@ExtendWith(MockitoExtension.class) // 클래스단에 해당 어노테이션을 달아, 클래스가 Mockito를 사용함을 명시적으로 알립니다.
class SaleServiceTest {

	@InjectMocks
	SaleService saleService;

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
		saleService = new SaleService(productRepository, purchaseRepository, saleRepository, tradeRepository);
	}

	@Test
	@DisplayName("판매 입찰 등록 테스트")
	void saveSaleTest() {
		//given

		doReturn(Optional.ofNullable(getProduct())).when(productRepository).findById(any(Long.class));
		Sale sale = getSaleReq().toEntity();
		sale.setProduct(getProduct());

		doReturn(false)
			.when(saleRepository)
			.existsByProductAndProductSize(any(Product.class), any(String.class));

		doReturn(Optional.ofNullable(getPurchase()))
			.when(purchaseRepository)
			.findByProductAndProductSize(any(Product.class), any(String.class));

		doReturn(getTrade(sale)).when(tradeRepository).save(any(Trade.class));

		//when

		saleService.saveSale(getSaleReq());

		//then

		verify(productRepository).findById(any(Long.class));
		verify(saleRepository).existsByProductAndProductSize(any(Product.class), any(String.class));
		verify(purchaseRepository).findByProductAndProductSize(any(Product.class), any(String.class));
		verify(tradeRepository).save(any(Trade.class));

	}

	@Test
	@DisplayName("판매입찰 등록 실패테스트(물품이없을때)")
	void saveSaleFailNotFoundProductTest() {
		//given

		doThrow(CommonException.class).when(productRepository).findById(any(Long.class));

		//when
		//then
		assertThatThrownBy(
			() ->
				saleService.saveSale(getSaleReq())
		).isInstanceOf(CommonException.class);

	}

	@Test
	@DisplayName("판매입찰 등록 실패테스트(중복된 판매물품일때 )")
	void saveSaleFailDuplicatePurchaseTest() {
		//given
		doReturn(Optional.ofNullable(getProduct())).when(productRepository).findById(any(Long.class));

		doReturn(true)
			.when(saleRepository)
			.existsByProductAndProductSize(any(Product.class), any(String.class));

		//when
		//then
		assertThatThrownBy(
			() ->
				saleService.saveSale(getSaleReq())
		).isInstanceOf(CommonException.class);

	}

	public SaleRequest getSaleReq() {
		return SaleRequest.builder()
			.userId(getRandomLong())
			.productId(getRandomLong())
			.productSize("256")
			.bidPrice("10000")
			.returnAddress("test/est/test")
			.build();
	}

	private Product getProduct() {
		return Product.builder()
			.id(getRandomLong())
			.name(getRandomString())
			.modelNumber(getRandomString())
			.releasePrice(getRandomString())
			.category(SHOES)
			.imgUrl(getRandomString())
			.brand(getRandomString())
			.build();
	}

	private Trade getTrade(Sale sale) {
		Trade trade = Trade.builder().purchase(getPurchase())
			.sale(sale)
			.status(TradeStatus.BID_COMPLETE)
			.build();
		return trade;
	}

	private Purchase getPurchase() {
		return Purchase.builder()
			.userId(getRandomLong())
			.productSize(getRandomString())
			.bidPrice(getRandomString())
			.shippingAddress(getRandomString())
			.tradeStatus(BID_PROGRESS)
			.product(getProduct())
			.build();
	}

}

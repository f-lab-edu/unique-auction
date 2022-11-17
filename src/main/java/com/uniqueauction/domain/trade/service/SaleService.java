package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.exception.ErrorCode.*;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
	private final ProductRepository productRepository;
	private final PurchaseRepository purchaseRepository;
	private final SaleRepository saleRepository;
	private final TradeRepository tradeRepository;

	@Transactional
	public Long saveSale(SaleRequest saleRequest) {
		/* sale 등록을 위한 product 조회 */
		Optional<Product> product = Optional.ofNullable(productRepository.findById(saleRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT)));

		Sale sale = saleRequest.toEntity();
		product.ifPresent(sale::setProduct);

		/* 판매 등록 */
		sale.changeTradeStatus(TradeStatus.BID_PROGRESS);

		if (!saleRepository.existsByProductAndProductSize(sale.getProduct(), sale.getProductSize())) {
			saleRepository.save(sale);
		} else {
			throw new CommonException(DUPLICATE_SALE);
		}

		/* trade 생성 여부 확인을 위한 puchase 검색 */
		Optional<Purchase> purchase = purchaseRepository.findByProductAndProductSize(sale.getProduct(),
			sale.getProductSize());

		purchase.ifPresent(p -> {
			Trade trade = Trade.builder().purchase(p)
				.sale(sale)
				.status(TradeStatus.BID_COMPLETE)
				.build();
			tradeRepository.save(trade);
		});

		return sale.getId();

	}
}

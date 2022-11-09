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
import com.uniqueauction.web.trade.request.PurchaseRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
	private final ProductRepository productRepository;
	private final PurchaseRepository purchaseRepository;
	private final SaleRepository saleRepository;
	private final TradeRepository tradeRepository;

	@Transactional
	public void savePurchase(PurchaseRequest purchaseRequest) {
		/* purchase 등록을 위한 product 조회 */
		Optional<Product> product = Optional.ofNullable(productRepository.findById(purchaseRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT)));

		Purchase purchase = purchaseRequest.toEntity();
		product.ifPresent(purchase::setProduct);

		/* 구매 등록 */
		purchase.changeTradeStatus(TradeStatus.BID_PROGRESS);
		if (!purchaseRepository.existsByProductAndProductSize(purchase.getProduct(), purchase.getProductSize())) {
			purchaseRepository.save(purchase);
		} else {
			throw new CommonException(DUPLICATE_PURCHASE);
		}

		/* trade 생성 여부 확인을 위한 sale 검색 */
		Optional<Sale> sale = saleRepository.findByProductAndProductSize(purchase.getProduct(),
			purchase.getProductSize());

		sale.ifPresent(s -> {
			Trade trade = Trade.builder().purchase(purchase)
				.sale(s)
				.status(TradeStatus.BID_COMPLETE)
				.build();
			tradeRepository.save(trade);
		});
	}
}

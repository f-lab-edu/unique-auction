package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.domain.trade.entity.TradeStatus.*;
import static com.uniqueauction.exception.ErrorCode.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.trade.entity.Purchase;
import com.uniqueauction.domain.trade.entity.Sale;
import com.uniqueauction.domain.trade.entity.Trade;
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
	public Long savePurchase(PurchaseRequest purchaseRequest) {
		/* purchase 등록을 위한 product 조회 */
		Product product = productRepository.findById(purchaseRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));
		/* product와 product size로 purchase 조회 */
		Purchase purchase = purchaseRepository.findByProductAndProductSize(product, purchaseRequest.getProductSize())
			.orElse(purchaseRequest.toEntity());

		/* 구매 등록 */
		purchase.savePurchase(purchase, purchaseRequest);
		purchaseRepository.save(purchase);

		/* trade 생성 여부 확인을 위한 sale 검색 */
		Optional<Sale> sale = saleRepository.findByProductAndProductSize(purchase.getProduct(),
			purchase.getProductSize());

		/* sale 데이터를 찾아서 판매 기간이 남아 있거나, 입찰 체결되지 않은 건, 판매 입찰이 구매 입찰보다 작은 금액일 경우 체결 */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate today = LocalDate.now();
		sale.ifPresent(s -> {
			LocalDate saleBidDueDate = LocalDate.parse(purchase.getBidDueDate(), formatter);
			if (!saleBidDueDate.isBefore(today)
				&& s.getTradeStatus() == BID_PROGRESS
				&& s.getBidPrice().compareTo(purchase.getBidPrice()) < 0) {
				Trade trade
					= Trade.builder()
					.purchase(purchase)
					.sale(s)
					.status(BID_COMPLETE)
					.build();
				tradeRepository.save(trade);

				/* 체결 상태를 업데이트 한다. */
				s.setTradeStatus(BID_COMPLETE);
				purchase.updateTradeStatus(BID_COMPLETE);
				saleRepository.save(s);
				purchaseRepository.save(purchase);
			}
		});
		return purchase.getId();
	}
}

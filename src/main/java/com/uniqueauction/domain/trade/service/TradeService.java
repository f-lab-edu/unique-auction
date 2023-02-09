package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;
import com.uniqueauction.domain.trade.repository.TradeRepository;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.web.trade.request.TradeRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeService {
	private final ProductRepository productRepository;
	private final TradeRepository tradeRepository;
	private final UserRepository userRepository;

	@Transactional
	public void createPurchase(TradeRequest tradeRequest) {
		/* trade 등록을 위한 user 조회 */
		User buyer = userRepository.findById(tradeRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		/* purchase 등록을 위한 product 조회 */
		Product product = productRepository.findById(tradeRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));

		/* product와 product size, 구매 입찰 중인 trade 조회 - 조회되는 것이 있으면 업데이트 하기 위함 */
		Trade trade = tradeRepository.findByProductAndProductSizeAndTradeStatusAndPriceLessThanEqual(product,
				tradeRequest.getProductSize(),
				TradeStatus.SALE_PROGRESS, tradeRequest.getPrice())
			.orElse(tradeRepository.findByPublisherIdAndProductAndProductSizeAndTradeStatus(buyer.getId(), product,
					tradeRequest.getProductSize(),
					TradeStatus.PURCHASE_PROGRESS)
				.orElse(tradeRequest.convertForBuyer(product)));

		/* 판매 입찰이 있는 경우 거래 체결을 위한 update, 기존 요청을 찾았을 경우 기존 요청 update  */
		if (trade.getTradeStatus() == TradeStatus.SALE_PROGRESS) {
			trade.updateTradeByBuyer(tradeRequest.getUserId(), tradeRequest.getPrice());
		} else if (trade.getTradeStatus() == TradeStatus.PURCHASE_PROGRESS) {
			trade.updateTrade(tradeRequest.getPrice(), tradeRequest.getShippingAddress());
		}

		/*
		판매자에게 message 전송
		 */
		tradeRepository.save(trade);
	}

	@Transactional
	public void createSale(TradeRequest tradeRequest) {
		/* trade 등록을 위한 user 조회 */
		User seller = userRepository.findById(tradeRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		/* sale 등록을 위한 product 조회 */
		Product product = productRepository.findById(tradeRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));

		/* product와 product size, 구매 입찰 중인 trade 조회 - 조회되는 것이 있으면 업데이트 하기 위함 */
		Trade trade = tradeRepository.findByProductAndProductSizeAndTradeStatusAndPriceGreaterThanEqual(product,
				tradeRequest.getProductSize(),
				TradeStatus.PURCHASE_PROGRESS,
				tradeRequest.getPrice())
			.orElse(tradeRepository.findByPublisherIdAndProductAndProductSizeAndTradeStatus(seller.getId(), product,
					tradeRequest.getProductSize(),
					TradeStatus.PURCHASE_PROGRESS)
				.orElse(tradeRequest.convertForSeller(product)));

		/* 구매 입찰이 있는 경우 거래 체결을 위한 update, 기존 요청을 찾았을 경우 기존 요청 update  */
		if (trade.getTradeStatus() == TradeStatus.PURCHASE_PROGRESS) {
			trade.updateTradeBySeller(tradeRequest.getUserId(), tradeRequest.getPrice());
		} else if (trade.getTradeStatus() == TradeStatus.SALE_PROGRESS) {
			trade.updateTrade(tradeRequest.getPrice(), tradeRequest.getShippingAddress());
		}

		/*
		구매자에게 message 전송
		 */
		tradeRepository.save(trade);
	}
}

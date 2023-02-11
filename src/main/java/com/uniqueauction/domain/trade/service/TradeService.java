package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.exception.ErrorCode.*;

import java.util.Optional;

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

		/* product와 product size, 판매 입찰 중인 trade 조회 - 조회되는 것이 있으면 거래 체결 하기 위함 */
		Optional<Trade> trade = tradeRepository.findByProductIdAndProductSizeAndTradeStatusAndPriceLessThanEqual(
			product.getId(),
			tradeRequest.getProductSize(),
			TradeStatus.SALE_PROGRESS, tradeRequest.getPrice());

		/* 구매자가 찾는 판매 요청 내역이 있을 경우 거래 체결 */
		if (trade.isPresent()) {
			trade.get().updateTradeByBuyer(tradeRequest.getUserId(), tradeRequest.getPrice());
		} else {
			/* 기존 요청한 구매 요청 있는지 조회 한다  */
			trade = tradeRepository.findByPublisherIdAndProductIdAndProductSizeAndTradeStatus(buyer.getId(),
				product.getId(),
				tradeRequest.getProductSize(),
				TradeStatus.PURCHASE_PROGRESS);

			/* 기존 요청 내역 존재시 업데이트, 없으면 신규 생성 */
			if (trade.isPresent()) {
				trade.get().updateTrade(tradeRequest.getPrice(), tradeRequest.getShippingAddress());
			} else {
				trade = Optional.ofNullable(tradeRequest.convertForSeller(product.getId()));
			}
		}

		/*
		판매자에게 message 전송
		 */
		trade.ifPresent(tradeRepository::save);
	}

	@Transactional
	public void createSale(TradeRequest tradeRequest) {
		/* trade 등록을 위한 user 조회 */
		User seller = userRepository.findById(tradeRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		/* sale 등록을 위한 product 조회 */
		Product product = productRepository.findById(tradeRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));

		/* product와 product size, 구매 입찰 중인 trade 조회 - 조회되는 것이 있으면 거래 체결 하기 위함 */
		Optional<Trade> trade = tradeRepository.findByProductIdAndProductSizeAndTradeStatusAndPriceLessThanEqual(
			product.getId(),
			tradeRequest.getProductSize(),
			TradeStatus.PURCHASE_PROGRESS, tradeRequest.getPrice());

		/* 판매자가 찾는 구매 요청이 있을 경우 거래 체결 */
		if (trade.isPresent()) {
			trade.get().updateTradeByBuyer(tradeRequest.getUserId(), tradeRequest.getPrice());
		} else {
			/* 기존 요청한 판매 요청 있는지 조회 한다  */
			trade = tradeRepository.findByPublisherIdAndProductIdAndProductSizeAndTradeStatus(seller.getId(),
				product.getId(),
				tradeRequest.getProductSize(),
				TradeStatus.SALE_PROGRESS);

			/* 기존 요청 내역 존재시 업데이트, 없으면 신규 생성 */
			if (trade.isPresent()) {
				trade.get().updateTrade(tradeRequest.getPrice(), tradeRequest.getShippingAddress());
			} else {
				trade = Optional.ofNullable(tradeRequest.convertForSeller(product.getId()));
			}
		}

		/*
		구매자에게 message 전송
		 */
		trade.ifPresent(tradeRepository::save);
	}
}

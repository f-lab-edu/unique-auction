package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.repository.TradeRepository;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.infrastructure.messaging.KafkaProducer;
import com.uniqueauction.web.trade.request.TradeRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BidService {
	private final ProductRepository productRepository;
	private final TradeRepository tradeRepository;
	private final UserRepository userRepository;
	private final KafkaProducer kafkaProducer;

	@Transactional
	public void createBid(TradeRequest tradeRequest) {
		/* trade 등록을 위한 user 조회 */
		User bidder = userRepository.findById(tradeRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		/* purchase 등록을 위한 product 조회 */
		Product product = productRepository.findById(tradeRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));

		/* 기존 요청한 구매 요청 있는지 조회 한다 */
		Trade trade = tradeRepository.findByPublisherIdAndProductIdAndProductSizeAndTradeStatus(
				bidder.getId(),
				product.getId(),
				tradeRequest.getProductSize(),
				tradeRequest.getTradeStatus())
			.orElse(tradeRequest.convert(product.getId(), tradeRequest.getTradeStatus()));

		/* 기존 요청 내역 존재시 업데이트, 없으면 신규 생성 */
		trade.updateTrade(tradeRequest.getPrice(), tradeRequest.getShippingAddress());

		/* 판매자에게 message 전송 */
		kafkaProducer.sendBid("bid-topic", trade);
	}
}


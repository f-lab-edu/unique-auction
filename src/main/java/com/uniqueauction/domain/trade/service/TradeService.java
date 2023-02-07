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
import com.uniqueauction.web.trade.request.TradeRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeService {
	private final ProductRepository productRepository;
	private final TradeRepository tradeRepository;
	private final UserRepository userRepository;

	@Transactional
	public Long bidPurchase(TradeRequest.SaveBidRequest saveBidRequest) {
		/* trade 등록을 위한 product 조회 */
		Product product = productRepository.findById(saveBidRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));

		/* trade 등록을 위한 user 조회 */
		User user = userRepository.findById(saveBidRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		/* buyer에 의한 trade 객체 생성 */
		Trade trade = saveBidRequest.convertForBuyer(user, product, saveBidRequest.getShippingAddress());

		tradeRepository.save(trade);
		return trade.getId();
	}

	@Transactional
	public Long bidSale(TradeRequest.SaveBidRequest saveBidRequest) {
		/* trade 등록을 위한 product 조회 */
		Product product = productRepository.findById(saveBidRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));

		/* trade 등록을 위한 user 조회 */
		User user = userRepository.findById(saveBidRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		/* seller 의한 trade 객체 생성 */
		Trade trade = saveBidRequest.convertForSeller(user, product, saveBidRequest.getShippingAddress());

		tradeRepository.save(trade);
		return trade.getId();
	}

	@Transactional
	public void createPurchase(TradeRequest.SaveTradeRequest saveTradeRequest) {
		/* trade 등록을 위한 user 조회 */
		User buyer = userRepository.findById(saveTradeRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		Trade trade = tradeRepository.findById(saveTradeRequest.getTradeId()).orElseThrow(() -> new CommonException(NOT_FOUND_TRADE));

		trade.createPurchase(buyer, saveTradeRequest.getShippingAdress());

		/*
		판매자에게 message 전송
		 */
		tradeRepository.save(trade);
	}

	@Transactional
	public void createSale(TradeRequest.SaveTradeRequest saveTradeRequest) {
		/* trade 등록을 위한 user 조회 */
		User seller = userRepository.findById(saveTradeRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		Trade trade = tradeRepository.findById(saveTradeRequest.getTradeId()).orElseThrow(() -> new CommonException(NOT_FOUND_TRADE));

		trade.createSale(seller, saveTradeRequest.getShippingAdress());

		/*
		구매자에게 message 전송
		 */
		tradeRepository.save(trade);
	}
}

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
	public Long requestPurchase(TradeRequest tradeRequest) {
		/* trade 등록을 위한 product 조회 */
		Product product = productRepository.findById(tradeRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));

		/* trade 등록을 위한 user 조회 */
		User user = userRepository.findById(tradeRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		/* buyer에 의한 trade 객체 생성 */
		Trade trade = tradeRequest.convertForBuyer(user, product, tradeRequest.getShippingAddress());

		tradeRepository.save(trade);
		return trade.getId();
	}

	@Transactional
	public Long requestSale(TradeRequest tradeRequest) {
		/* trade 등록을 위한 product 조회 */
		Product product = productRepository.findById(tradeRequest.getProductId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));

		/* trade 등록을 위한 user 조회 */
		User user = userRepository.findById(tradeRequest.getUserId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		/* seller 의한 trade 객체 생성 */
		Trade trade = tradeRequest.convertForSeller(user, product, tradeRequest.getShippingAddress());

		tradeRepository.save(trade);
		return trade.getId();
	}
}

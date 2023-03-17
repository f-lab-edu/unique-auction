package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.exception.ErrorCode.*;

import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.repository.TradeRepository;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.infrastructure.kafka.KafkaProducer;
import com.uniqueauction.infrastructure.redis.RedisLockRepository;
import com.uniqueauction.web.trade.request.TradeRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BidService {
	private final ProductRepository productRepository;
	private final TradeRepository tradeRepository;
	private final UserRepository userRepository;
	private final KafkaProducer kafkaProducer;
	private final RedisLockRepository redisLockRepository;

	@Transactional
	public void createBid(TradeRequest tradeRequest) {

		/* trade 등록을 위한 user 조회 */
		User bidder = findUserById(tradeRequest.getUserId());

		/* purchase 등록을 위한 product 조회 */
		Product product = findProductById(tradeRequest.getProductId());

		RLock lock = redisLockRepository.getLock("trade_" + product.getId());
		try {
			boolean locked = redisLockRepository.tryLock(lock, 5,
				60);
			if (locked) {
				/* 기존 요청한 구매 요청 있는지 조회 한다 */
				Trade trade = findOrCreateTrade(bidder, product, tradeRequest);
				/* 기존 요청 업데이트 */
				trade.updateTrade(tradeRequest.getPrice(), tradeRequest.getShippingAddress());
				/* Bid 저장 */
				saveBid(trade);
				/* 요청 내역 Kafka 메시지 전송 */
				kafkaProducer.sendBid("bid-topic", trade);
			} else {
				throw new CommonException(LOCK_TIMEOUT);
			}
		} finally {
			redisLockRepository.unlock(lock);
		}
	}

	private User findUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));
	}

	private Product findProductById(Long productId) {
		return productRepository.findById(productId)
			.orElseThrow(() -> new CommonException(NOT_FOUND_PRODUCT));
	}

	private Trade findOrCreateTrade(User bidder, Product product, TradeRequest tradeRequest) {
		return tradeRepository.findByPublisherIdAndProductIdAndProductSizeAndTradeStatus(
				bidder.getId(),
				product.getId(),
				tradeRequest.getProductSize(),
				tradeRequest.getTradeStatus())
			.orElse(tradeRequest.convert(product.getId(), tradeRequest.getTradeStatus()));
	}

	private void saveBid(Trade bid) {
		tradeRepository.save(bid);
	}
}


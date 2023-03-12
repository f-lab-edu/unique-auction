package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.exception.ErrorCode.*;

import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.trade.entity.TradeStatus;
import com.uniqueauction.domain.trade.repository.TradeRepository;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeService {
	private final UserRepository userRepository;
	private final JavaMailSender emailSender;
	private final TradeRepository tradeRepository;

	@KafkaListener(
		topics = "bid-topic"
	)
	public void processBid(Trade bid) {
		Optional<Trade> existingTrade;

		/* trade 등록을 위한 user 조회 */
		User bidder = userRepository.findById(bid.getPublisherId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		System.out.println(bid.getTradeStatus() + ", " + bid.getPrice());

		TradeStatus tradeStatus = (bid.getTradeStatus() == TradeStatus.SALE_PROGRESS) ? TradeStatus.PURCHASE_PROGRESS :
			TradeStatus.SALE_PROGRESS;

		System.out.println(bid.getTradeStatus() + ", " + bid.getPrice());

		/* product와 product size, 판매 입찰 중인 trade 조회 - 조회되는 것이 있으면 거래 체결 하기 위함 */
		if (bid.getTradeStatus() == TradeStatus.PURCHASE_PROGRESS) {
			existingTrade = tradeRepository.findByProductIdAndProductSizeAndTradeStatusAndPriceLessThanEqual(
				bid.getProductId(),
				bid.getProductSize(),
				tradeStatus, bid.getPrice());
		} else {
			existingTrade = tradeRepository.findByProductIdAndProductSizeAndTradeStatusAndPriceGreaterThanEqual(
				bid.getProductId(),
				bid.getProductSize(),
				tradeStatus, bid.getPrice());
		}

		existingTrade.ifPresentOrElse(
			trade -> {
				trade.tradeComplete(bid.getPrice(), bid.getPublisherId(), bid.getTradeStatus());
				tradeRepository.save(trade);
				// 거래체결 E Mail 전송
				sendTradeConfirmationEmail(bidder.getEmail(), bid.getId());
			}, () -> tradeRepository.save(bid));
	}

	private void sendTradeConfirmationEmail(String toEmail, Long bidId) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("CONSUMER KAFKA RECEIVED MESSAGE 거래 체결 안내");
		message.setText(bidId + "거래 완료");
		emailSender.send(message);
	}
}


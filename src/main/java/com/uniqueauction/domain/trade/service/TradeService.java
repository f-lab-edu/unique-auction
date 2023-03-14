package com.uniqueauction.domain.trade.service;

import static com.uniqueauction.exception.ErrorCode.*;

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

	@KafkaListener(topics = "bid-topic")
	public void processBid(Trade bid) {
		/* 거래를 체결할 수 있는 데이터 존재 체크 */
		Trade existingTrade = checkExistingTrade(bid);

		/* 존재시 거래 체결 없으면 요청을 저장 */
		if (existingTrade == null) {
			saveBid(bid);
		} else {
			completeTrade(existingTrade, bid);
		}
	}

	private User findBidder(Trade bid) {
		return userRepository.findById(bid.getPublisherId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));
	}

	private Trade checkExistingTrade(Trade bid) {
		TradeStatus tradeStatus = (bid.getTradeStatus() == TradeStatus.SALE_PROGRESS)
			? TradeStatus.PURCHASE_PROGRESS
			: TradeStatus.SALE_PROGRESS;

		if (bid.getTradeStatus() == TradeStatus.PURCHASE_PROGRESS) {
			return tradeRepository.findSaleBid(bid, tradeStatus).stream().findFirst().orElse(null);
		} else {
			return tradeRepository.findPurchaseBid(bid, tradeStatus).stream().findFirst().orElse(null);
		}
	}

	private void completeTrade(Trade trade, Trade bid) {
		/* Bidder 조회 */
		User bidder = findBidder(bid);
		trade.tradeComplete(bid.getPrice(), bid.getPublisherId(), bid.getTradeStatus());
		tradeRepository.save(trade);
		sendTradeConfirmationEmail(bidder.getEmail(), bid.getId());
	}

	private void saveBid(Trade bid) {
		tradeRepository.save(bid);
	}

	private void sendTradeConfirmationEmail(String toEmail, Long bidId) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("CONSUMER KAFKA RECEIVED MESSAGE 거래 체결 안내");
		message.setText(bidId + "거래 완료");
		emailSender.send(message);
	}
}


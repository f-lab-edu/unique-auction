package com.uniqueauction.domain.message.service;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.uniqueauction.domain.trade.entity.Trade;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final JavaMailSender emailSender;
	private final UserRepository userRepository;

	@KafkaListener(
		topics = "trades"
	)
	public void sendSimpleMessage(Trade trade) {
		SimpleMailMessage message = new SimpleMailMessage();
		User user = userRepository.findById(trade.getPublisherId())
			.orElseThrow(() -> new CommonException(NOT_FOUND_USER));

		message.setTo(user.getEmail());
		message.setSubject("CONSUMER KAFKA RECEIVED MESSAGE 거래 체결 안내");
		message.setText(trade.getId() + "거래 완료");
		emailSender.send(message);
	}
}

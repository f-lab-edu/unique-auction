package com.uniqueauction.infrastructure.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.uniqueauction.domain.trade.entity.Trade;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
	private final KafkaTemplate<String, Trade> kafkaTemplate;

	public void sendBid(String topic, Trade trade) {
		this.kafkaTemplate.send(topic, trade);
	}
}

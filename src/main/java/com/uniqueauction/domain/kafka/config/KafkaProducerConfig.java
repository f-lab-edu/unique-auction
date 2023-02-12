package com.uniqueauction.domain.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.uniqueauction.domain.trade.entity.Trade;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

	@Bean
	public Map<String, Object> kafkaConfigProducer() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return properties;
	}

	@Bean
	public ProducerFactory<String, Trade> producerFactory() {
		return new DefaultKafkaProducerFactory<>(kafkaConfigProducer());
	}

	@Bean
	public KafkaTemplate<String, Trade> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}

package com.uniqueauction.domain.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.uniqueauction.domain.trade.entity.Trade;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Bean
	public Map<String, Object> kafkaConfigConsumer() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "trade");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return properties;
	}

	@Bean
	public ConsumerFactory<String, Trade> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(kafkaConfigConsumer(),
			new StringDeserializer(), new JsonDeserializer<>(Trade.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Trade> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Trade> kafkaListenerContainerFactory
			= new ConcurrentKafkaListenerContainerFactory<>();
		kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
		return kafkaListenerContainerFactory;
	}
}

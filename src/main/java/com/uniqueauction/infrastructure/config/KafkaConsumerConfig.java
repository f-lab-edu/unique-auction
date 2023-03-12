package com.uniqueauction.infrastructure.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;

	@Bean
	public Map<String, Object> consumerConfig() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return properties;
	}

	@Bean
	public ConsumerFactory<String, Trade> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfig(),
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

package com.uniqueauction;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractContainerBaseTest {
	static final String REDIS_IMAGE = "redis:latest";
	static final GenericContainer<?> REDIS_CONTAINER;
	static final DockerImageName KAFKA_IMAGE = DockerImageName.parse("confluentinc/cp-kafka:6.2.1");

	static {
		REDIS_CONTAINER = new GenericContainer<>(REDIS_IMAGE)
			.withExposedPorts(6379);
		REDIS_CONTAINER.start();

		try (KafkaContainer KAFKA_CONTAINER = new KafkaContainer(KAFKA_IMAGE)) {
			KAFKA_CONTAINER.start();
			///testKafkaFunctionality(KAFKA_CONTAINER.getBootstrapServers());
		}
	}

	@DynamicPropertySource
	static void registerRedisProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.redis.host", REDIS_CONTAINER::getHost);
		registry.add("spring.redis.port", REDIS_CONTAINER::getFirstMappedPort);
	}
}

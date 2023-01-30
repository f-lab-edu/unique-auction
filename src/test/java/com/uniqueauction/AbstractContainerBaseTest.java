package com.uniqueauction;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
public abstract class AbstractContainerBaseTest {
	static final String REDIS_IMAGE = "redis:latest";
	static final GenericContainer<?> REDIS_CONTAINER;

	static {
		REDIS_CONTAINER = new GenericContainer<>(REDIS_IMAGE)
			.withExposedPorts(6379);
		REDIS_CONTAINER.start();
	}

	@DynamicPropertySource
	static void registerRedisProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.redis.host", REDIS_CONTAINER::getHost);
		registry.add("spring.redis.port", REDIS_CONTAINER::getFirstMappedPort);
	}
}

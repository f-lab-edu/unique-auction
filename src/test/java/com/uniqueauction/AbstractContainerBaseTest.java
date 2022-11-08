package com.uniqueauction;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractContainerBaseTest {
	static final String MYSQL_IMAGE = "mysql:8";
	static final MySQLContainer<?> MY_SQL_CONTAINER;
	static final String REDIS_IMAGE = "redis:6-alpine";
	static final GenericContainer REDIS_CONTAINER;

	static {

		REDIS_CONTAINER = new GenericContainer<>(REDIS_IMAGE)
			.withExposedPorts(6379)
			.withReuse(true);
		REDIS_CONTAINER.start();
		MY_SQL_CONTAINER = new MySQLContainer<>(MYSQL_IMAGE).withReuse(true);
		MY_SQL_CONTAINER.start();
	}

	@DynamicPropertySource
	public static void overrideProps(DynamicPropertyRegistry registry) {
		registry.add("spring.redis.host", REDIS_CONTAINER::getHost);
		registry.add("spring.redis.port", () -> "" + REDIS_CONTAINER.getMappedPort(6379));
	}
}

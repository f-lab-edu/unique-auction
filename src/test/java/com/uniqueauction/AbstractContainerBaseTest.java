package com.uniqueauction;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractContainerBaseTest {
	static final String MYSQL_IMAGE = "mysql:8";
	static final MySQLContainer MY_SQL_CONTAINER;
	static {
		MY_SQL_CONTAINER = (MySQLContainer) new MySQLContainer(MYSQL_IMAGE).withInitScript("schema-test.sql");
		MY_SQL_CONTAINER.start();
	}
}

package com.uniqueauction;

import java.util.Random;

import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

public class CommonUtilMethod {

	public static String getRandomString() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public static Long getRandomLong() {
		return new Random().nextLong();
	}
}

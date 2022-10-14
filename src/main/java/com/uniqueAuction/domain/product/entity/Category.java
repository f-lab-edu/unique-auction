package com.uniqueAuction.domain.product.entity;

import static com.uniqueAuction.exception.ErrorCode.*;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.uniqueAuction.exception.advice.CommonException;

public enum Category {

	SHOES,
	CLOTHES,
	FASHIONETC,
	ELECTRONIC,

	;

	@JsonCreator
	public static Category setValue(String key) {
		Optional<Category> category = Arrays.stream(Category.values())
			.filter(ct -> ct.toString().equals(key))
			.findAny();
		return category.orElseThrow(() -> new CommonException(NOT_FOUND_CATEGORY));
	}
}

package com.uniqueAuction.domain.product.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Image {

	private Long id;
	private Long productId;
	private String imgUrl;

}

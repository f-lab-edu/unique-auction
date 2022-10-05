package com.uniqueAuction.domain.product.entity;

import lombok.Builder;

@Builder
public class Image {

    private Long id;
    private Long productId;
    private String imgUrl;


}

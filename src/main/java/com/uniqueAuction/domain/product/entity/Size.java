package com.uniqueAuction.domain.product.entity;


import lombok.Builder;

@Builder
public class Size {

    private Long id;
    private Long productId;
    private int size;

}

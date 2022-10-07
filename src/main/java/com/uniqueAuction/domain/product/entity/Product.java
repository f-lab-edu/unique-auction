package com.uniqueAuction.domain.product.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Product {

    private Long id;
    private String modelNumber;
    private String releasePrice;
    private String size;
    private Category category;
    private String brand;
}

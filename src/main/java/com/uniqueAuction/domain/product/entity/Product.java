package com.uniqueAuction.domain.product.entity;


import com.uniqueAuction.web.product.request.ProductSaveRequest;
import com.uniqueAuction.web.product.request.ProductUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class Product {

//    private Long id;
    private String modelNumber;
    private String releasePrice;
    private String size;
    private String category;
    private String stock;


    public static Product of(ProductSaveRequest productSaveRequest) {
        return new Product.ProductBuilder()
                .modelNumber(productSaveRequest.getModelNumber())
                .releasePrice(productSaveRequest.getReleasePrice())
                .size(productSaveRequest.getSize())
                .category(productSaveRequest.getCategory())
                .stock(productSaveRequest.getStock())
                .build();

    }

    public  Product of(ProductUpdateRequest productUpdateRequest) {
        return new Product.ProductBuilder()
                .modelNumber(productUpdateRequest.getModelNumber())
                .releasePrice(productUpdateRequest.getReleasePrice())
                .size(productUpdateRequest.getSize())
                .category(productUpdateRequest.getCategory())
                .stock(productUpdateRequest.getStock())
                .build();

    }
}

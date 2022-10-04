package com.uniqueAuction.web.product.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uniqueAuction.domain.product.entity.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
public class ProductSaveRequest {

    @NotBlank(message = "모델번호는 공백은 입력할 수 없습니다.")
    private String modelNumber;

    @NotBlank(message = "발매가는 공백은 입력할 수 없습니다.")
    private String releasePrice;

    @NotBlank(message = "사이즈는 공백은 입력할 수 없습니다.")
    private String size;

    @NotBlank(message = "카테고리는 공백은 입력할 수 없습니다.")
    private String category;

    @NotBlank(message = "수량은 공백은 입력할 수 없습니다.")
    private String stock;



    @Builder
    public ProductSaveRequest(@JsonProperty("name")String modelNumber
            , @JsonProperty("name")String releasePrice
            , @JsonProperty("size")String size
            , @JsonProperty("category")String category
            , @JsonProperty("stock")String stock) {
        this.modelNumber = modelNumber;
        this.releasePrice = releasePrice;
        this.size = size;
        this.category = category;
        this.stock = stock;
    }


    public static Product convert(ProductSaveRequest productSaveRequest){
        return Product.builder()
                .modelNumber(productSaveRequest.getModelNumber())
                .releasePrice(productSaveRequest.getReleasePrice())
                .size(productSaveRequest.getSize())
                .category(productSaveRequest.getCategory())
                .stock(productSaveRequest.getStock())
                .build();
    }
}

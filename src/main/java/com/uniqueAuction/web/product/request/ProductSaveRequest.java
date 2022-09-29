package com.uniqueAuction.web.product.request;

import com.uniqueAuction.domain.product.entity.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Builder
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

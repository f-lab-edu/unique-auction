package com.uniqueAuction.web.product.request;

import com.uniqueAuction.domain.product.entity.Category;
import com.uniqueAuction.domain.product.entity.Image;
import com.uniqueAuction.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductUpdateRequest {

    @NotBlank(message = "상품ID는 공백은 입력할 수 없습니다.")
    private Long productId;

    @NotBlank(message = "이미지ID는 공백은 입력할 수 없습니다.")
    private Long imageId;

    @NotBlank(message = "모델번호는 공백은 입력할 수 없습니다.")
    private String modelNumber;

    @NotBlank(message = "발매가는 공백은 입력할 수 없습니다.")
    private String releasePrice;

    @NotBlank(message = "색상은 공백은 입력할 수 없습니다.")
    private String color;

    @NotBlank(message = "카테고리는 공백은 입력할 수 없습니다.")
    private Category category;

    @NotBlank(message = "이미지주소는 공백은 입력할 수 없습니다.")
    private String imgUrl;

    @NotBlank(message = "브랜드는 공백은 입력할 수 없습니다.")
    private String brand;


    public  Product toProduct(){
        return Product.builder()
                .id(this.productId)
                .modelNumber(this.modelNumber)
                .releasePrice(this.releasePrice)
                .color(this.color)
                .category(this.category)
                .brand(this.brand)
                .build();
    }

    public Image toImage(){
        return Image.builder()
                .id(this.imageId)
                .productId(this.productId)
                .imgUrl(this.imgUrl)
                .build();
    }

}

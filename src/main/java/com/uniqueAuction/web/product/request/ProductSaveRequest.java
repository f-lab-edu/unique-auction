package com.uniqueAuction.web.product.request;

import com.uniqueAuction.domain.product.entity.Category;
import com.uniqueAuction.domain.product.entity.Image;
import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductSaveRequest {

    @NotBlank(message = "모델번호는 공백은 입력할 수 없습니다.")
    private String modelNumber;

    @NotBlank(message = "발매가는 공백은 입력할 수 없습니다.")
    private String releasePrice;

    @NotBlank(message = "색상은 공백은 입력할 수 없습니다.")
    private String color;

    @NotNull(message = "카테고리는 공백은 입력할 수 없습니다.")
    private Category category;

    @NotBlank(message = "이미지주소는 공백은 입력할 수 없습니다.")
    private String imgUrl;

    @NotBlank(message = "브랜드는 공백은 입력할 수 없습니다.")
    private String brand;




    public  Product toProduct(){
        return Product.builder()
                .modelNumber(this.modelNumber)
                .releasePrice(this.releasePrice)
                .color(this.color)
                .category(this.category)
                .brand(this.brand)
                .build();
    }

    public Image toImage(Long productId){
        return Image.builder()
                .productId(productId)
                .imgUrl(this.imgUrl)
                .build();
    }

}

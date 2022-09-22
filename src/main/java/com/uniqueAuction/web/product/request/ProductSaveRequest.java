package com.uniqueAuction.web.product.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@ToString
@Getter
public class ProductSaveRequest {

    @NotBlank(message = "공백은 입력할 수 없습니다.")
    private String modelNumber;

    @NotBlank(message = "공백은 입력할 수 없습니다.")
    private String releasePrice;

    @NotBlank(message = "공백은 입력할 수 없습니다.")
    private String size;

    @NotBlank(message = "공백은 입력할 수 없습니다.")
    private String category;

    @NotBlank(message = "공백은 입력할 수 없습니다.")
    private String stock;

}

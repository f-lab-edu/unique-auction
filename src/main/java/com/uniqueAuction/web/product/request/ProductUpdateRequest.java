package com.uniqueAuction.web.product.request;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uniqueAuction.domain.product.entity.Category;
import com.uniqueAuction.domain.product.entity.Image;
import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.entity.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductUpdateRequest {

	@NotBlank(message = "상품ID는 공백은 입력할 수 없습니다.")
	private Long productId;

	@NotNull(message = "사이즈는 공백은 입력할 수 없습니다.")
	private String[] size;

	@NotBlank(message = "이미지ID는 공백은 입력할 수 없습니다.")
	private Long imageId;

	@NotBlank(message = "모델번호는 공백은 입력할 수 없습니다.")
	private String modelNumber;

	@NotBlank(message = "발매가는 공백은 입력할 수 없습니다.")
	private String releasePrice;

	@NotBlank(message = "카테고리는 공백은 입력할 수 없습니다.")
	private Category category;

	@NotBlank(message = "이미지주소는 공백은 입력할 수 없습니다.")
	private String imgUrl;

	@NotBlank(message = "브랜드는 공백은 입력할 수 없습니다.")
	private String brand;

	public Product toProduct() {
		return Product.builder()
			.id(this.productId)
			.modelNumber(this.modelNumber)
			.releasePrice(this.releasePrice)
			.category(this.category)
			.brand(this.brand)
			.build();
	}

	public Image toImage() {
		return Image.builder()
			.id(this.imageId)
			.productId(this.productId)
			.imgUrl(this.imgUrl)
			.build();
	}

	public List<Size> toSize() {
		return Arrays.stream(size)
			.map(s -> Size.builder().productId(this.productId).size(s).build())
			.collect(Collectors.toList());

	}

}

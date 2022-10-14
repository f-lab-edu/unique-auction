package com.uniqueAuction.web.product.request;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uniqueAuction.domain.product.entity.Category;
import com.uniqueAuction.domain.product.entity.Image;
import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.entity.TradeItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductSaveRequest {

	@NotBlank(message = "모델번호는 공백은 입력할 수 없습니다.")
	private String modelNumber;

	@NotBlank(message = "발매가는 공백은 입력할 수 없습니다.")
	private String releasePrice;

	@NotNull(message = "사이즈는 공백은 입력할 수 없습니다.")
	private String[] size;

	@NotNull(message = "카테고리는 공백은 입력할 수 없습니다.")
	private Category category;

	@NotBlank(message = "이미지주소는 공백은 입력할 수 없습니다.")
	private String imgUrl;

	@NotBlank(message = "브랜드는 공백은 입력할 수 없습니다.")
	private String brand;

	public Product toProduct() {
		return Product.builder()
			.modelNumber(this.modelNumber)
			.releasePrice(this.releasePrice)
			.category(this.category)
			.brand(this.brand)
			.build();
	}

	public Image toImage(Long productId) {
		return Image.builder()
			.productId(productId)
			.imgUrl(this.imgUrl)
			.build();
	}

	public List<TradeItem> toSize(Long productId) {
		return Arrays.stream(size)
			.map(s -> TradeItem.builder().productId(productId).size(s).build())
			.collect(Collectors.toList());

	}

}

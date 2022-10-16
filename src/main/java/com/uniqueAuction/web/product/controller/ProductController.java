package com.uniqueauction.web.product.controller;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.service.ImageService;
import com.uniqueauction.domain.product.service.ProductService;
import com.uniqueauction.domain.product.service.TradeItemService;
import com.uniqueauction.exception.advice.CommonValidationException;
import com.uniqueauction.web.product.request.ProductSaveRequest;
import com.uniqueauction.web.product.request.ProductUpdateRequest;
import com.uniqueauction.web.response.CommonResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;
	private final ImageService imageService;
	private final TradeItemService tradeItemService;

	@GetMapping("/products/{id}")
	public CommonResponse selectProduct(@PathVariable Long id) {
		Product product = productService.productFindById(id);

		return CommonResponse.success(product);
	}

	@PostMapping("/products")
	public CommonResponse saveProduct(@RequestBody @Validated ProductSaveRequest productSaveRequest,
		BindingResult result) {

		if (result.hasErrors()) {
			throw new CommonValidationException(MISSING_PARAMETER);
		}

		long productId = productService.save(productSaveRequest.toProduct());

		imageService.save(productSaveRequest.toImage(productId));

		tradeItemService.save(productSaveRequest.toSize(productId));

		return CommonResponse.success();
	}

	@PatchMapping("/products/{id}")
	public CommonResponse updateProduct(@RequestBody @Validated ProductUpdateRequest productUpdateRequest,
		BindingResult result) {

		if (result.hasErrors()) {
			throw new CommonValidationException(MISSING_PARAMETER);
		}

		Product updateProduct = productService.updateProduct(productUpdateRequest.toProduct());

		imageService.update(productUpdateRequest.toImage());

		tradeItemService.update(productUpdateRequest.toSize());

		return CommonResponse.success(updateProduct);
	}

	@DeleteMapping("/products/{id}")
	public CommonResponse DeleteProduct(@PathVariable Long id) {

		productService.deleteProduct(id);

		return CommonResponse.success();
	}

}

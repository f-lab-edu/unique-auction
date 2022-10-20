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
import com.uniqueauction.domain.product.service.ProductService;
import com.uniqueauction.exception.advice.CommonValidationException;
import com.uniqueauction.web.product.request.ProductSaveRequest;
import com.uniqueauction.web.product.request.ProductUpdateRequest;
import com.uniqueauction.web.response.CommonResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;

	@GetMapping("/products/{id}")
	public CommonResponse selectProduct(@PathVariable Long id) {
		Product product = productService.findById(id);

		return CommonResponse.success(product);
	}

	@GetMapping("/products/search/{searProduct}")
	public CommonResponse selectProducts(@PathVariable String searProduct) {
		return CommonResponse.success(productService.findByNameOrModelNumber(searProduct));
	}

	@PostMapping("/products")
	public CommonResponse saveProduct(@RequestBody @Validated ProductSaveRequest productSaveRequest,
		BindingResult result) {

		if (result.hasErrors()) {
			String defaultMessage = result.getFieldError().getDefaultMessage();
			throw new CommonValidationException(MISSING_PARAMETER.setMissingParameterMsg(defaultMessage));
		}

		productService.save(productSaveRequest.toEntity());

		return CommonResponse.success();
	}

	@PatchMapping("/products/{id}")
	public CommonResponse updateProduct(@RequestBody @Validated ProductUpdateRequest productUpdateRequest,
		BindingResult result) {

		if (result.hasErrors()) {
			String defaultMessage = result.getFieldError().getDefaultMessage();
			throw new CommonValidationException(MISSING_PARAMETER.setMissingParameterMsg(defaultMessage));
		}

		Product updateProduct = productService.update(productUpdateRequest.toEntity());

		return CommonResponse.success(updateProduct);
	}

	@DeleteMapping("/products/{id}")
	public CommonResponse deleteProduct(@PathVariable Long id) {

		productService.deleteProduct(id);

		return CommonResponse.success();
	}

}

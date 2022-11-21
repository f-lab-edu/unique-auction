package com.uniqueauction.web.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.service.ProductService;
import com.uniqueauction.web.product.request.ProductSaveRequest;
import com.uniqueauction.web.product.request.ProductUpdateRequest;
import com.uniqueauction.web.response.CommonResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;

	@GetMapping("/products/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse selectProduct(@PathVariable Long id) {
		Product product = productService.findById(id);

		return CommonResponse.success(product);
	}

	@GetMapping("/products/search/{search}")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse selectProducts(@PathVariable String search) {
		return CommonResponse.success(productService.findByNameOrModelNumber(search));
	}

	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public CommonResponse saveProduct(@RequestBody @Validated ProductSaveRequest productSaveRequest,
		BindingResult result) {

		Product product = productService.save(productSaveRequest.toEntity());

		return CommonResponse.success(product);
	}

	@PatchMapping("/products/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse updateProduct(@RequestBody @Validated ProductUpdateRequest productUpdateRequest,
		BindingResult result) {

		Product updateProduct = productService.update(productUpdateRequest.toEntity());

		return CommonResponse.success(updateProduct);
	}

	@DeleteMapping("/products/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CommonResponse deleteProduct(@PathVariable Long id) {
		productService.delete(id);
		return CommonResponse.success();
	}

}

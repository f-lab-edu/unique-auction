package com.uniqueauction.web.product.controller;

import java.util.Optional;

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
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.domain.product.service.ProductService;
import com.uniqueauction.web.product.request.ProductSaveRequest;
import com.uniqueauction.web.product.request.ProductSearchRequest;
import com.uniqueauction.web.product.request.ProductUpdateRequest;
import com.uniqueauction.web.response.CommonResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;
	private final ProductRepository productRepository;

	@GetMapping("/products/{id}")
	public CommonResponse<?> selectProduct(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);
		return CommonResponse.success(product);
	}

	@GetMapping("/products/search")
	public CommonResponse selectProducts(@RequestBody @Validated ProductSearchRequest productSearchRequest,
		BindingResult result) {
		return CommonResponse.success(productService.findByNameOrModelNumber(productSearchRequest.getProductName(),
			productSearchRequest.getModelNumber()));
	}

	@PostMapping("/products")
	public CommonResponse<?> saveProduct(@RequestBody @Validated ProductSaveRequest productSaveRequest,
		BindingResult result) {

		System.out.println("save");
		productRepository.save(productSaveRequest.toEntity());
		System.out.println("save end");
		return CommonResponse.success();
	}

	@PatchMapping("/products/{id}")
	public CommonResponse updateProduct(@RequestBody @Validated ProductUpdateRequest productUpdateRequest,
		BindingResult result) {

		productService.update(productUpdateRequest.toEntity());
		Optional<Product> updateProduct = productRepository.findById(productUpdateRequest.getProductId());
		return CommonResponse.success(updateProduct);
	}

	@DeleteMapping("/products/{id}")
	public CommonResponse<?> deleteProduct(@PathVariable Long id) {

		productRepository.deleteById(id);

		return CommonResponse.success();
	}

}

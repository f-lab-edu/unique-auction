package com.uniqueAuction.web.product.controller;


import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.repository.ImageRepository;
import com.uniqueAuction.domain.product.repository.SizeRepository;
import com.uniqueAuction.domain.product.service.ImageService;
import com.uniqueAuction.domain.product.service.ProductService;
import com.uniqueAuction.exception.advice.CommonValidationException;
import com.uniqueAuction.web.product.request.ProductSaveRequest;
import com.uniqueAuction.web.product.request.ProductUpdateRequest;
import com.uniqueAuction.web.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.uniqueAuction.exception.ErrorCode.MISSING_PARAMETER;


@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
    private final ImageService imageService;


    @GetMapping("/products/{id}")
    public CommonResponse selectProduct(@PathVariable Long id) {
        Product product = productService.productFindById(id);

        return CommonResponse.success(product);
    }


    @PostMapping("/products")
    public CommonResponse saveProduct(@RequestBody @Validated ProductSaveRequest productSaveRequest, BindingResult result) {

        if (result.hasErrors()) {
            throw new CommonValidationException(MISSING_PARAMETER);
        }

        long productId = productService.save(productSaveRequest.toProduct());

        imageService.save(productSaveRequest.toImage(productId));

        return CommonResponse.success();
    }


    @PatchMapping("/products/{id}")
    public CommonResponse updateProduct(@PathVariable Long id, @RequestBody @Validated ProductUpdateRequest productUpdateRequest, BindingResult result) {

        if (result.hasErrors()) {
            throw new CommonValidationException(MISSING_PARAMETER);
        }

        Product updateProduct = productService.updateProduct(id, productUpdateRequest.toProduct());

        return CommonResponse.success(updateProduct);
    }


    @DeleteMapping("/products/{id}")
    public CommonResponse DeleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return CommonResponse.success();
    }

}

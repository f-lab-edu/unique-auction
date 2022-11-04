package com.uniqueauction.domain.product.repository;

import static com.uniqueauction.domain.product.entity.Category.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniqueauction.AbstractContainerBaseTest;
import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.service.ProductService;
import com.uniqueauction.web.product.request.ProductSaveRequest;
import com.uniqueauction.web.product.request.ProductUpdateRequest;

@TestContainerBase
class ProductRepositoryImplTest extends AbstractContainerBaseTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	private Product product;

	private Long pId;

	@BeforeEach
	public void set() {
		product = getSaveReq().toEntity();
		pId = productRepository.save(product).getId();
	}

	@AfterEach
	public void clear() {
		productRepository.deleteAll();
	}

	@Test
	void productSaveTest() {
		//then
		assertThat(pId).isEqualTo(1L);
	}

	@Test
	void productSelectTest() {

		//when
		Optional<Product> product = productRepository.findById(pId);

		//then
		assertThat(product.get().getModelNumber()).isEqualTo("123");

	}

	@Test
	void productUpdateTest() {
		product = getUpdateReq(pId).toEntity();
		//when
		productRepository.save(product);
		Optional<Product> update = productRepository.findById(pId);
		//then
		assertThat(update.get().getModelNumber()).isEqualTo("457");
	}

	@Test
	void productDeleteTest() {

		Optional<Product> result = productRepository.findById(pId);

		//when
		productRepository.delete(result.get());

		//then
		assertThat(productRepository.findAll().size()).isEqualTo(0);
	}

	private ProductSaveRequest getSaveReq() {
		return ProductSaveRequest.builder()
			.productName("상품1")
			.modelNumber("123")
			.releasePrice("10000")
			.category(SHOES)
			.imgUrl("/test/test")
			.build();
	}

	private ProductUpdateRequest getUpdateReq(Long pId) {
		return ProductUpdateRequest.builder()
			.productId(pId)
			.productName("상품2")
			.modelNumber("457")
			.releasePrice("10000")
			.category(CLOTHES)
			.imgUrl("/test/test")
			.build();
	}
}

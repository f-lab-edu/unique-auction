package com.uniqueauction.domain.product.repository;

import static com.uniqueauction.domain.product.entity.Category.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.web.product.request.ProductSaveRequest;
import com.uniqueauction.web.product.request.ProductUpdateRequest;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ProductRepositoryImplTest {

	@Autowired
	private ProductRepository productRepository;

	private Product product;

	private long pId;

	@BeforeEach
	public void set() {
		pId = productRepository.save(getSaveReq().toEntity());
	}

	@AfterEach
	public void clear() {
		productRepository.deleteAll();
	}

	@Test
	@Order(1)
	void productSaveTest() {
		//then
		assertThat(pId).isEqualTo(1L);
	}

	@Test
	@Order(2)
	void productSelectTest() {

		//when
		product = productRepository.findById(pId);

		//then
		assertThat(product.getModelNumber()).isEqualTo("123");

	}

	@Test
	@Order(3)
	void productUpdateTest() {

		//when
		Product update = productRepository.update(getUpdateReq(pId).toEntity());

		//then
		assertThat(update.getModelNumber()).isEqualTo("457");
	}

	@Test
	@Order(4)
	void productDeleteTest() {

		//when
		productRepository.delete(pId);

		//then
		assertThat(productRepository.findByAll().size()).isEqualTo(0);
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

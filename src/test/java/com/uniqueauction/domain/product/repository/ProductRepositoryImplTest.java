package com.uniqueauction.domain.product.repository;

import static com.uniqueauction.domain.product.entity.Category.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
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

	@AfterEach
	public void clear() {
		productRepository.deleteAll();
	}

	@Test
	@Order(1)
	void 사용자저장() {

		//given
		long pId = productRepository.save(getSaveReq().toEntity());
		//then
		assertThat(pId).isEqualTo(1L);

	}

	@Test
	@Order(2)
	void 사용자조회() {

		//given
		long pId = productRepository.save(getSaveReq().toEntity());

		//when
		product = productRepository.findById(pId);

		//then
		assertThat(product.getModelNumber()).isEqualTo("123");

	}

	@Test
	@Order(3)
	void 사용자수정() {
		//given
		long pId = productRepository.save(getSaveReq().toEntity());

		//when
		Product update = productRepository.update(getUpdateReq(pId).toEntity());

		//then
		assertThat(update.getModelNumber()).isEqualTo("457");
	}

	@Test
	@Order(4)
	void 사용자삭제() {
		//given
		productRepository.save(getSaveReq().toEntity());

		//when
		productRepository.delete(4L);

		//then
		assertThat(productRepository.findByAll().size()).isEqualTo(0);
	}

	private ProductSaveRequest getSaveReq() {
		return ProductSaveRequest.builder()
			.modelNumber("123")
			.releasePrice("10000")
			.category(SHOES)
			.build();
	}

	private ProductUpdateRequest getUpdateReq(Long pId) {
		return ProductUpdateRequest.builder()
			.productId(pId)
			.modelNumber("457")
			.releasePrice("10000")
			.category(CLOTHES)
			.build();
	}
}

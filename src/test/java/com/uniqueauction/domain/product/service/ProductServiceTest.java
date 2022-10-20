package com.uniqueauction.domain.product.service;

import static com.uniqueauction.domain.product.entity.Category.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.web.product.request.ProductSaveRequest;
import com.uniqueauction.web.product.request.ProductUpdateRequest;

/**
 * service 테스트
 *
 * @ExtendWith(MockitoExtension.class):가짜 객체 주입
 * @Mock: Mock 객체를 만들어 반환해주는 어노테이션
 * @Spy: Stub하지 않은 메소드들은 원본 메소드 그대로 사용하는 어노테이션
 * @InjectMocks: @Mock 또는 @Spy로 생성된 가짜 객체를 자동으로 주입시켜주는 어노테이션
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Spy
	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void clear() {
		productRepository.deleteAll();
	}
	//
	// @AfterEach
	// public void clear() {
	// 	productRepository.deleteAll();
	// }

	@Test
	void productSaveTest() {

		//given
		lenient().doReturn(0L).when(productService).save(getSaveProduct());

		//when
		long pId = productService.save(getSaveReq().toEntity());

		assertThat(0L).isEqualTo(pId);
		//then

	}

	@Test
	void productDetailSelectTest() {

		Long pId = 1L;

		//given
		doReturn(getSaveProduct()).when(productRepository).findById(any(Long.class));

		//when

		Product product = productService.findById(pId);

		//then
		assertThat(product.getModelNumber()).isEqualTo("123");
		assertThat(product.getReleasePrice()).isEqualTo("10000");
		assertThat(product.getCategory()).isEqualTo(SHOES);

	}

	@Test
	void productUpdateTest() {

		Long pId = 1L;

		Product updateProduct = getUpdateReq(pId).toEntity();
		//given
		doReturn(updateProduct).when(productRepository).update(updateProduct);
		//when
		Product product = productService.update(updateProduct);

		//then
		assertThat(product.getModelNumber()).isEqualTo("457");
		assertThat(product.getReleasePrice()).isEqualTo("10000");
		assertThat(product.getCategory()).isEqualTo(SHOES);
	}

	@Test
	void productDeleteTest() {

		//given
		Long pId = 1L;
		doNothing().when(productRepository).delete(1L);
		doReturn(null).when(productRepository).findByAll();

		//when
		productService.deleteProduct(pId);

		assertThat(productService.findByAll()).isEqualTo(null);

	}

	private Product getSaveProduct() {
		return getSaveReq().toEntity();
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
			.category(SHOES)
			.imgUrl("/test/test")
			.build();
	}

	public List<Product> getListProduct(ProductSaveRequest saveProduct) {
		List<Product> list = new ArrayList();
		list.add(saveProduct.toEntity());
		return list;
	}

}

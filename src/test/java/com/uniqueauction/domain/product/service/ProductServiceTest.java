package com.uniqueauction.domain.product.service;

import static com.uniqueauction.CommonUtilMethod.*;
import static com.uniqueauction.domain.product.entity.Category.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.product.repository.ProductRepository;
import com.uniqueauction.exception.advice.CommonNotFoundException;
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
@ExtendWith(MockitoExtension.class) // 클래스단에 해당 어노테이션을 달아, 클래스가 Mockito를 사용함을 명시적으로 알립니다.
class ProductServiceTest {

	@Spy
	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	private Product saveProduct;
	private Product updateProduct;
	private final Long pId = getRandomLong();

	@BeforeEach
	public void set() {
		saveProduct = getSaveProduct();
		updateProduct = getUpdateProduct(pId);
	}

	@Test
	void productSaveTest() {

		//given
		doReturn(getSaveProduct()).when(productService).save(any(Product.class));

		//when
		Product product = productService.save(saveProduct);

		assertThat("상품1").isEqualTo(product.getName());
		assertThat("123").isEqualTo(product.getModelNumber());
		assertThat("10000").isEqualTo(product.getReleasePrice());
		assertThat(SHOES).isEqualTo(product.getCategory());
		assertThat("/test/test").isEqualTo(product.getImgUrl());
		//then

		verify(productService).save(saveProduct);

	}

	@Test
	void productDetailSelectTest() {

		//given
		doReturn(Optional.of(getSaveProduct())).when(productRepository).findById(pId);
		//when

		Product product = productService.findById(pId);

		assertThat(product.getModelNumber()).isEqualTo("123");
		assertThat(product.getReleasePrice()).isEqualTo("10000");
		assertThat(product.getCategory()).isEqualTo(SHOES);

		verify(productService).findById(pId);

	}

	@Test
	void productUpdateTest() {

		//given
		doReturn(updateProduct).when(productService).update(updateProduct);
		//when

		Product product = productService.update(updateProduct);

		//then
		assertThat(product.getModelNumber()).isEqualTo("457");
		assertThat(product.getReleasePrice()).isEqualTo("10000");
		assertThat(product.getCategory()).isEqualTo(SHOES);

		verify(productService).update(updateProduct);
	}

	@Test
	void productDeleteTest() {

		//given

		doReturn(getSaveProduct()).when(productService).save(any(Product.class));
		doNothing().when(productService).delete(anyLong());

		//when
		productService.save(saveProduct);
		productService.delete(pId);

		assertThatThrownBy(
			() ->
				productService.findById(pId)
		).isInstanceOf(CommonNotFoundException.class);

		verify(productService).delete(pId);

	}

	private Product getSaveProduct() {
		return getSaveReq().toEntity();
	}

	private Product getUpdateProduct(Long id) {
		return getUpdateReq(id).toEntity();
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
}

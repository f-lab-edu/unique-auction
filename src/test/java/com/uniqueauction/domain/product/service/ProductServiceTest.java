package com.uniqueauction.domain.product.service;

import static com.uniqueauction.domain.product.entity.Category.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceTest {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	private Product saveProduct;
	private Product updateProduct;
	private Long pId = 1L;

	@BeforeEach
	public void set() {
		saveProduct = getSaveProduct();
		updateProduct = getUpdateProduct(pId);
	}

	@Disabled
	@Test
	void productSaveTest() {

		//given
		lenient().doReturn(any(Product.class)).when(productRepository).save(any(Product.class));

		//when
		Long save = productService.save(saveProduct);

		System.out.println("@@");
		System.out.println(save);

		assertThat(1L).isEqualTo(save);
		//then

		verify(productService).save(saveProduct);

	}

	@Disabled
	@Test
	void productDetailSelectTest() {

		Long pId = 1L;

		//given
		doReturn(any(Product.class)).when(productRepository).findById(pId);
		//when

		Product product = productService.findById(pId);

		assertThat(product.getModelNumber()).isEqualTo("123");
		assertThat(product.getReleasePrice()).isEqualTo("10000");
		assertThat(product.getCategory()).isEqualTo(SHOES);

		verify(productService).findById(pId);

	}

	@Disabled
	@Test
	void productUpdateTest() {

		Long pId = 1L;

		//given
		doReturn(any(Product.class)).when(productRepository).findById(pId);
		doReturn(updateProduct).when(productRepository).save(updateProduct);
		//when

		Product product = productService.update(updateProduct);

		//then
		assertThat(product.getModelNumber()).isEqualTo("457");
		assertThat(product.getReleasePrice()).isEqualTo("10000");
		assertThat(product.getCategory()).isEqualTo(SHOES);

		verify(productService).update(updateProduct);
	}

	@Disabled
	@Test
	void productDeleteTest() {

		//given

		doReturn(pId).when(productService).save(saveProduct);
		doNothing().when(productService).delete(pId);

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

	public List<Product> getListProduct(ProductSaveRequest saveProduct) {
		List<Product> list = new ArrayList<>();
		list.add(saveProduct.toEntity());
		return list;
	}

}

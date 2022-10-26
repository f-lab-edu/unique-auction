package com.uniqueauction.domain.product.service;

import static com.uniqueauction.domain.product.entity.Category.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest
@AutoConfigureMockMvc
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

	@Test
	void productSaveTest() {

		Product saveProduct = getSaveProduct();
		//given
		doReturn(0L).when(productService).save(saveProduct);

		//when
		long pId = productService.save(saveProduct);

		assertThat(0L).isEqualTo(pId);
		//then

		verify(productService).save(saveProduct);

	}

	@Test
	void productDetailSelectTest() {

		Long pId = 1L;

		//given
		doReturn(getSaveProduct()).when(productRepository).findById(pId);

		//when

		Optional<Product> product = productService.findById(pId);

		//then
		assertThat(product.getModelNumber()).isEqualTo("123");
		assertThat(product.getReleasePrice()).isEqualTo("10000");
		assertThat(product.getCategory()).isEqualTo(SHOES);

		verify(productService).findById(pId);

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

		verify(productService).update(updateProduct);
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

		verify(productService).deleteProduct(pId);

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

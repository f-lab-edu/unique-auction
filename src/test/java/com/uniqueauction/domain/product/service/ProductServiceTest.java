package com.uniqueauction.domain.product.service;

import static com.uniqueauction.domain.product.entity.Category.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.uniqueauction.TestContainerBase;
import org.junit.jupiter.api.AfterEach;
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
@TestContainerBase
class ProductServiceTest {

	@Spy
	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductRepository productRepository;

	private Product product;
	private Long pId;

	@BeforeEach
	public void set() {
		product = getSaveReq().toEntity();
		lenient().doReturn(1L).when(productService).save(product);
		pId = productService.save(product);
	}

	@AfterEach
	public void clear() {
		productRepository.deleteAll();
	}

	@Test
	void productSaveTest() {
		Product saveProduct = getSaveProduct();
		//given
		lenient().doReturn(1L).when(productService).save(saveProduct);
		//when
		pId = productService.save(saveProduct);
		assertThat(pId).isEqualTo(1L);
		//then
		verify(productService).save(saveProduct);
	}

	@Test
	void productDetailSelectTest() {
		//given
		doReturn(Optional.of(getSaveProduct())).when(productRepository).findById(pId);
		//when
		Optional<Product> product = productRepository.findById(pId);
		//then
		assertThat(product.get().getModelNumber()).isEqualTo("123");
		assertThat(product.get().getReleasePrice()).isEqualTo("10000");
		assertThat(product.get().getCategory()).isEqualTo(SHOES);
		verify(productRepository).findById(pId);
	}

	@Test
	void productUpdateTest() {
		Product updateProduct = getUpdateReq(pId).toEntity();
		//given
		doReturn(updateProduct).when(productService).update(updateProduct);
		//when
		Product product = productService.update(updateProduct);
		//then
		assertThat(updateProduct.getModelNumber()).isEqualTo("457");
		assertThat(updateProduct.getReleasePrice()).isEqualTo("10000");
		assertThat(updateProduct.getCategory()).isEqualTo(SHOES);

		verify(productService).update(updateProduct);
	}

	@Test
	void productDeleteTest() {
		doNothing().when(productService).delete(pId);
		doReturn(null).when(productRepository).findById(pId);
		productService.delete(pId);
		assertThat(productRepository.findById(pId)).isEqualTo(null);
		verify(productService).delete(pId);
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
		List<Product> list = new ArrayList<>();
		list.add(saveProduct.toEntity());
		return list;
	}

}

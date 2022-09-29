package com.uniqueAuction.domain.product.service;

import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.repository.ProductRepository;
import com.uniqueAuction.web.product.request.ProductSaveRequest;
import com.uniqueAuction.web.product.request.ProductUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.uniqueAuction.web.product.request.ProductSaveRequest.saveToProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


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


    @InjectMocks
    private ProductService productService;


    @Mock
    private ProductRepository productRepository;

    private ProductSaveRequest saveReq;

    private  ProductUpdateRequest updateReq;



    @BeforeEach
    public void setup() {

        saveReq = new ProductSaveRequest("123", "10000", "284", "운동화", "1");
        updateReq = new ProductUpdateRequest("456", "10000", "284", "운동화", "12");

    }


    @Test
    void 상품을저장한다() {

        //given

        doReturn(saveReq).when(productRepository).saveProduct(any(Product.class));

        //when

        productService.saveProduct(saveToProduct(saveReq));

        //then
    }


    @Test
    void 상품상세조회한다() {

        Long pId = 1L;
        //given
        doReturn(getSaveProduct()).when(productRepository).productFindById(any(Long.class));

        //when

        Product product = productService.productFindById(pId);

        //then
        assertThat(product.getModelNumber()).isEqualTo("123");
        assertThat(product.getReleasePrice()).isEqualTo("10000");
        assertThat(product.getSize()).isEqualTo("284");
        assertThat(product.getCategory()).isEqualTo("운동화");
        assertThat(product.getStock()).isEqualTo("1");

    }

    @Test
    void 상품을수정한다() {

        Long pId = 1L;

        Product updateProduct = updateReq.updateToProduct();
        //given
        doReturn(updateProduct).when(productRepository).update(pId,updateProduct);
//        given(productRepository.update(pId,updateProduct)).willReturn(updateProduct);
        //when
        Product product = productService.updateProduct(pId,updateProduct);


        //then
        assertThat(product.getModelNumber()).isEqualTo("456");
        assertThat(product.getReleasePrice()).isEqualTo("10000");
        assertThat(product.getSize()).isEqualTo("284");
        assertThat(product.getCategory()).isEqualTo("운동화");
        assertThat(product.getStock()).isEqualTo("12");

    }


    @Test
    void 상품을삭제한다() {

        //given
        Long pId = 1L;
        doNothing().when(productRepository).delete(1L);

        //when
       productService.deleteProduct(pId);

    }

    private Product getSaveProduct(){
        return new Product("123", "10000", "284", "운동화", "1");
    }

}
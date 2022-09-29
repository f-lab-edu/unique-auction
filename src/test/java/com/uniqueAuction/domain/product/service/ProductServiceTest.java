package com.uniqueAuction.domain.product.service;

import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.domain.product.repository.ProductRepository;
import com.uniqueAuction.web.product.request.ProductSaveRequest;
import com.uniqueAuction.web.product.request.ProductUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.uniqueAuction.web.product.request.ProductSaveRequest.convert;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;


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



//    @Test
//    void 상품을저장한다() {
//
//        //given
//
//        doNothing().when(productRepository).saveProduct(getSaveProduct());
//        doReturn(getListProduct(getSaveReq())).when(productRepository).findByAll();
//
//        //when
//
//        productService.saveProduct(convert(getSaveReq()));
//        //then
//
//        System.out.println(productService.findByAll().size());
//
//    }


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

        Product updateProduct = getUpdateReq().convert();
        //given
        doReturn(updateProduct).when(productRepository).update(pId, updateProduct);
        //when
        Product product = productService.updateProduct(pId, updateProduct);


        //then
        assertThat(product.getModelNumber()).isEqualTo("457");
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
        doReturn(0).when(productRepository).findByAll().size();

        //when
        productService.deleteProduct(pId);

        System.out.println();

    }

    private Product getSaveProduct() {
        return convert(getSaveReq());
    }

    private ProductSaveRequest getSaveReq() {
        return ProductSaveRequest.builder()
                .modelNumber("123")
                .releasePrice("10000")
                .size("284")
                .category("운동화")
                .stock("1").build();
    }

    private ProductUpdateRequest getUpdateReq() {
        return ProductUpdateRequest.builder()
                .modelNumber("457")
                .releasePrice("10000")
                .size("284")
                .category("운동화")
                .stock("12").build();
    }

    public List<Product> getListProduct(ProductSaveRequest saveProduct) {
        List<Product> list = new ArrayList();
        list.add(convert(saveProduct));
        return list;
    }

}
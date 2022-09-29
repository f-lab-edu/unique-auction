package com.uniqueAuction.domain.product.repository;

import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.web.product.request.ProductSaveRequest;
import com.uniqueAuction.web.product.request.ProductUpdateRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uniqueAuction.web.product.request.ProductSaveRequest.convert;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ProductRepositoryImplTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;


    @AfterEach
    public void clear() {
        productRepository.clear();
    }

    @Test
    @Order(1)
    void 사용자저장() {

        //given
        productRepository.saveProduct(convert(getSaveReq()));
        //then
        assertThat(productRepository.findByAll().size()).isEqualTo(1);

    }

    @Test
    @Order(2)
    void 사용자조회() {

        //given
        productRepository.saveProduct(convert(getSaveReq()));

        System.out.println(productRepository.findByAll().get(0).getModelNumber());

        //when
        product = productRepository.productFindById(2L);

        //then
        assertThat(product.getModelNumber()).isEqualTo("123");

    }

    @Test
    @Order(3)
    void 사용자수정() {
        //given
        productRepository.saveProduct(convert(getSaveReq()));

        //when
        Product update = productRepository.update(3L, getUpdateReq().convert());

        //then
        assertThat(update.getModelNumber()).isEqualTo("457");
    }


    @Test
    @Order(4)
    void 사용자삭제() {
        //given
        productRepository.saveProduct(convert(getSaveReq()));

        //when
       productRepository.delete(4L);

        //then
        assertThat(productRepository.findByAll().size()).isEqualTo(0);
    }



    private ProductSaveRequest getSaveReq() {
        return  ProductSaveRequest.builder()
                .modelNumber("123")
                .releasePrice("10000")
                .size("284")
                .category("운동화")
                .stock("1").build();
    }

    private ProductUpdateRequest getUpdateReq() {
        return  ProductUpdateRequest.builder()
                .modelNumber("457")
                .releasePrice("10000")
                .size("284")
                .category("dnsehdghk")
                .stock("1").build();
    }
}
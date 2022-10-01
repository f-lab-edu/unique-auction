package com.uniqueAuction.web.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniqueAuction.domain.product.service.ProductService;
import com.uniqueAuction.exception.advice.product.ProductControllerAdvice;
import com.uniqueAuction.web.product.request.ProductSaveRequest;
import com.uniqueAuction.web.product.request.ProductUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {


    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ProductService productService;


    private ProductSaveRequest saveReq;

    private ProductUpdateRequest updateReq;

    @BeforeEach
    public void setup() {
        mockMvc =
                MockMvcBuilders.standaloneSetup(new ProductController(productService))
                        .setControllerAdvice(new ProductControllerAdvice())
                        .addFilters(new CharacterEncodingFilter("UTF-8", true))
                        .build();

        saveReq = ProductSaveRequest.builder()
                .modelNumber("123")
                .releasePrice("10000")
                .size("284")
                .category("운동화")
                .stock("1").build();

        updateReq = ProductUpdateRequest.builder()
                .modelNumber("457")
                .releasePrice("10000")
                .size("284")
                .category("dnsehdghk")
                .stock("1").build();

    }

    @Test
    @DisplayName("상품 저장 완료가 되면 status  200을 반환한다.")
    void 상품저장테스트() throws Exception {


        mockMvc.perform(

                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8")
                                .content(objectMapper.writeValueAsString(saveReq)))
                .andExpect(status().isOk());
    }


    @Test
    void 상품업데이트테스트() throws Exception {


//        when(productService.updateProduct(1L, updateRequest)).thenReturn(updateRequest.updateProduct());


        mockMvc.perform(
                        patch("/products/" + 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8")
                                .content(objectMapper.writeValueAsString(updateReq)))
                .andExpect(status().isOk());
    }


    @Test
    void 상품삭제테스트() throws Exception {
        Long id = 1L;


        mockMvc.perform(
                        delete("/products/" + 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8")
                                .content(String.valueOf(id)))
                .andExpect(status().isOk());
    }


    @Test
    void 상품상세조회테스트() throws Exception {
        Long id = 1L;

        mockMvc.perform(
                        get("/products/" + 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8")
                                .content(String.valueOf(id)))
                .andExpect(status().isOk());
    }

}
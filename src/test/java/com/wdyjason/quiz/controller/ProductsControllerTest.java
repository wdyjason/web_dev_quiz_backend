package com.wdyjason.quiz.controller;

import com.wdyjason.quiz.enity.ProductsEntity;
import com.wdyjason.quiz.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductsController productsController;

    @Autowired
    ProductsRepository productsRepository;

    int savedId;

    @BeforeEach
    public void setUp() {

        ProductsEntity toSave = ProductsEntity.builder()
                .id(1)
                .name("test")
                .unit("unit")
                .price(2)
                .imgUrl("imgUrl")
                .build();
        productsRepository.deleteAll();
        savedId = productsRepository.save(toSave).getId();

    }

    @Test
    void should_get_product_list_success() throws Exception {

        mockMvc.perform(get("/product/list"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("test")))
                .andExpect(jsonPath("$[0].unit", is("unit")))
                .andExpect(jsonPath("$[0].imgUrl", is("imgUrl")))
                .andExpect(jsonPath("$[0].price", is(2)))
                .andExpect(status().isOk());
    }
}
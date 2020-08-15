package com.wdyjason.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdyjason.quiz.enity.ProductsEntity;
import com.wdyjason.quiz.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductsController productsController;

    @Autowired
    ProductsRepository productsRepository;

    @BeforeEach
    public void setUp() {
        productsRepository.deleteAll();
    }

    @Test
    void should_get_product_list_success() throws Exception {

        ProductsEntity toSave = ProductsEntity.builder()
                .name("test")
                .unit("unit")
                .price(2)
                .imgUrl("imgUrl")
                .quantity(3)
                .build();
        int savedId = productsRepository.save(toSave).getId();
        mockMvc.perform(get("/product/list"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(savedId)))
                .andExpect(jsonPath("$[0].name", is("test")))
                .andExpect(jsonPath("$[0].unit", is("unit")))
                .andExpect(jsonPath("$[0].imgUrl", is("imgUrl")))
                .andExpect(jsonPath("$[0].price", is(2)))
                .andExpect(jsonPath("$[0].quantity", is(3)))
                .andExpect(status().isOk());
    }

    @Test
    void should_get_order_list_success() throws Exception {

        ProductsEntity toSave = ProductsEntity.builder()
                .name("test")
                .unit("unit")
                .price(2)
                .imgUrl("imgUrl")
                .quantity(3)
                .build();
        int savedId = productsRepository.save(toSave).getId();

        ProductsEntity toSave1 = ProductsEntity.builder()
                .name("test1")
                .unit("unit1")
                .price(2)
                .imgUrl("imgUrl1")
                .quantity(0)
                .build();

        productsRepository.save(toSave1);

        mockMvc.perform(get("/order/list"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(savedId)))
                .andExpect(jsonPath("$[0].name", is("test")))
                .andExpect(jsonPath("$[0].unit", is("unit")))
                .andExpect(jsonPath("$[0].imgUrl", is("imgUrl")))
                .andExpect(jsonPath("$[0].price", is(2)))
                .andExpect(jsonPath("$[0].quantity", is(3)))
                .andExpect(status().isOk());

    }

    @Test
    void should_create_one_success() throws Exception {

        ProductsEntity toSave = ProductsEntity.builder()
                .name("test")
                .unit("unit")
                .price(2)
                .imgUrl("imgUrl")
                .build();
        ObjectMapper ObjectMapper = new ObjectMapper();
        String putStr = ObjectMapper.writeValueAsString(toSave);
        mockMvc.perform(put("/order").content(putStr).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(1, productsRepository.count());
    }
}
package com.wdyjason.quiz.service;

import com.wdyjason.quiz.dto.ProductsDto;
import com.wdyjason.quiz.enity.ProductsEntity;
import com.wdyjason.quiz.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ProductsServiceTest {
    @Mock
    ProductsService productsService;

    @Mock
    ProductsRepository productsRepository;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        productsService = new ProductsService(productsRepository);
    }

    @Test
    void should_get_product_list_success() {
        ProductsEntity toReturn = ProductsEntity.builder()
                .id(1)
                .name("test")
                .price(2)
                .unit("unit")
                .imgUrl("url")
                .build();
        when(productsRepository.findAll()).thenReturn(Arrays.asList(toReturn));

        List<ProductsDto> resDtoList =  productsService.getProductList();

        assertEquals(Arrays.asList(toReturn.toDto()), resDtoList);
    }

}
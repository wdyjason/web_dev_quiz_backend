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
import static org.mockito.Mockito.*;
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
                .quantity(3)
                .build();
        when(productsRepository.findAll()).thenReturn(Arrays.asList(toReturn));

        List<ProductsDto> resDtoList =  productsService.getProductList();

        assertEquals(Arrays.asList(toReturn.toDto()), resDtoList);
    }

    @Test
    void should_get_order_list_success() {
        ProductsEntity toReturn = ProductsEntity.builder()
                .id(1)
                .name("test")
                .price(2)
                .unit("unit")
                .imgUrl("url")
                .quantity(3)
                .build();

        ProductsEntity toReturn1 = ProductsEntity.builder()
                .id(2)
                .name("test")
                .price(2)
                .unit("unit")
                .imgUrl("url")
                .quantity(0)
                .build();
        when(productsRepository.findAll()).thenReturn(Arrays.asList(toReturn, toReturn1));

        List<ProductsDto> resDtoList =  productsService.getOrderList();

        assertEquals(Arrays.asList(toReturn.toDto()), resDtoList);
    }

    @Test
    void should_save_one_success() {
        ProductsEntity toSave = ProductsEntity.builder()
                .name("test")
                .price(2)
                .unit("unit")
                .imgUrl("url")
                .build();
        ProductsEntity toReturn = ProductsEntity.builder()
                .id(1)
                .name("test")
                .price(2)
                .unit("unit")
                .imgUrl("url")
                .quantity(0)
                .build();

        when(productsRepository.save(toSave)).thenReturn(toReturn);

        productsService.createOne(toSave);

        verify(productsRepository, times(1)).save(toSave);
    }

    @Test
    void should_patch_one_success() {
        productsService.patchOneQuantity(1, 2);
        verify(productsRepository, times(1)).patchOneQuantity(1, 2);
    }

}
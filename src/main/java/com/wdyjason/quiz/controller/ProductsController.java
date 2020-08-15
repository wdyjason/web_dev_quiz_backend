package com.wdyjason.quiz.controller;

import com.wdyjason.quiz.dto.ProductsDto;
import com.wdyjason.quiz.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/product/list")
    public List<ProductsDto> getALLProducts() {
        return productsService.getProductList();
    }

    @GetMapping("/order/list")
    public List<ProductsDto> getOrders() {
        return productsService.getOrderList();
    }

    @PutMapping("/order")
    public void createOne(@RequestBody ProductsDto dto) {
        productsService.createOne(dto.toEntity());
    }

    @PatchMapping("/order/{id}")
    public ResponseEntity patchOneQuantity(@PathVariable Integer id,
                                           @RequestParam Integer quantity) {
        if (id == null || quantity == null) {
            return ResponseEntity.badRequest().build();
        }
        productsService.patchOneQuantity(id, quantity);
        return ResponseEntity.ok().build();
    }
}

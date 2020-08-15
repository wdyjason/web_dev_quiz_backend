package com.wdyjason.quiz.service;

import com.wdyjason.quiz.dto.ProductsDto;
import com.wdyjason.quiz.enity.ProductsEntity;
import com.wdyjason.quiz.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<ProductsDto> getProductList() {
        return productsRepository.findAll().stream().map(ProductsEntity::toDto).collect(Collectors.toList());
    }

    public List<ProductsDto> getOrderList() {
        return productsRepository.findAll()
                .stream()
                .filter(f -> f.getQuantity() > 0)
                .map(ProductsEntity::toDto)
                .collect(Collectors.toList());
    }

    public void createOne(ProductsEntity toSave) {
        toSave.setQuantity(0);
        productsRepository.save(toSave);
    }

    @Transactional
    public void patchOneQuantity(int id, int quantity) {
        productsRepository.patchOneQuantity(id, quantity);
    }
}

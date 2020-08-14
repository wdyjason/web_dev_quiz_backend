package com.wdyjason.quiz.repository;

import com.wdyjason.quiz.enity.ProductsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsEntity,Integer> {

    List<ProductsEntity> findAll();
}

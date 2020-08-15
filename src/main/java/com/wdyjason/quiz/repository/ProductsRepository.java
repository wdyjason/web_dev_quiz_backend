package com.wdyjason.quiz.repository;

import com.wdyjason.quiz.enity.ProductsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsEntity,Integer> {

    List<ProductsEntity> findAll();

    @Modifying(flushAutomatically = true)
    @Query("UPDATE ProductsEntity p SET p.quantity=?2 WHERE p.id=?1")
    void patchOneQuantity(int id, int quantity);
}

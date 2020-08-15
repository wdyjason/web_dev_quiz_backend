package com.wdyjason.quiz.dto;

import com.wdyjason.quiz.enity.ProductsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
    private Integer id;

    private String name;

    private Integer price;

    private String unit;

    private String imgUrl;

    private Integer quantity;

    public ProductsEntity toEntity() {
        return ProductsEntity.builder()
                .quantity(0)
                .imgUrl(imgUrl)
                .name(name)
                .price(price)
                .unit(unit)
                .build();
    }
}

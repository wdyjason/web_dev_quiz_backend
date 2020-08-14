package com.wdyjason.quiz.dto;

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
}

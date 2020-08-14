package com.wdyjason.quiz.enity;

import com.wdyjason.quiz.dto.ProductsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer price;

    private String unit;

    private String imgUrl;

    private Integer quantity;

    public ProductsDto toDto() {
        return ProductsDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .unit(unit)
                .imgUrl(imgUrl)
                .quantity(quantity)
                .build();
    }
}

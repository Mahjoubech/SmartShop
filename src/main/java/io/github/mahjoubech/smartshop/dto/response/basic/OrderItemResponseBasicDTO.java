package io.github.mahjoubech.smartshop.dto.response.basic;

import io.github.mahjoubech.smartshop.dto.response.detail.ProductResponseDetailDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponseBasicDTO {
    private ProductResponseBasicDTO product;
    private Integer quantity;
    private BigDecimal lineTotal;
}

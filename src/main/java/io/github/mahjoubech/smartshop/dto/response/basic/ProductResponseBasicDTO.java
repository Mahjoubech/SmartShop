package io.github.mahjoubech.smartshop.dto.response.basic;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductResponseBasicDTO {
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;
}

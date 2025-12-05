package io.github.mahjoubech.smartshop.dto.response.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.mahjoubech.smartshop.dto.response.detail.ClientResponseDetailDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.OrderItemResponseDetailDTO;
import io.github.mahjoubech.smartshop.model.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseBasicAdminDTO {
    private String id;
    @JsonFormat(pattern = "dd MMMM yyyy ", locale = "fr")
    private LocalDate date ;
    private BigDecimal TVA;
    private BigDecimal totalTTC;
    private BigDecimal remainingAmount;
    private OrderStatus status;
    private ClientResponseBasicDTO client;
}

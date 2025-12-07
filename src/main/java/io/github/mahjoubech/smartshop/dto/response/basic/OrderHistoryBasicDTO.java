package io.github.mahjoubech.smartshop.dto.response.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.mahjoubech.smartshop.model.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class OrderHistoryBasicDTO {
    private String orderId;
    @JsonFormat(pattern = "dd MMMM yyyy ", locale = "fr")
    private LocalDate date;
    private BigDecimal totalTTC;
    private OrderStatus status;
}

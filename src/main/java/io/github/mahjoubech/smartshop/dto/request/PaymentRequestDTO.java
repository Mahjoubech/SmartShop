package io.github.mahjoubech.smartshop.dto.request;

import io.github.mahjoubech.smartshop.model.enums.PayementType;
import io.github.mahjoubech.smartshop.model.enums.PaymentStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    @NotNull(message = "Order ID cannot be null")
    private String orderId;
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
    @NotNull(message = "Payment type cannot be null")
    private PayementType type;
}

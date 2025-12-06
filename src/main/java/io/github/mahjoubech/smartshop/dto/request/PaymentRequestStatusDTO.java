package io.github.mahjoubech.smartshop.dto.request;

import io.github.mahjoubech.smartshop.model.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequestStatusDTO {
    @NotNull(message = "Payment status cannot be null")
    private PaymentStatus paymentStatus;
}

package io.github.mahjoubech.smartshop.dto.response.detail;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.mahjoubech.smartshop.dto.response.basic.OrderResponseBasicAdminDTO;
import io.github.mahjoubech.smartshop.model.enums.PayementType;
import io.github.mahjoubech.smartshop.model.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponseDetailDTO {
    private String id;
    private OrderResponseBasicAdminDTO order;
    private PayementType type;
    private PaymentStatus status;
    private BigDecimal amount;
    @JsonFormat(pattern = "dd MMMM yyyy ", locale = "fr")
    private String datePayment;
    @JsonFormat(pattern = "dd MMMM yyyy ", locale = "fr")
    private String dateDeposit;
}

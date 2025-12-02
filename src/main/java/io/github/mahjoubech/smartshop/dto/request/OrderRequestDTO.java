package io.github.mahjoubech.smartshop.dto.request;

import io.github.mahjoubech.smartshop.model.entity.OrderItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
@NotNull(message = "Client  cannot be null")
private String clientId;
private List<OrderItemRequestDTO> orderItems;
private String codePromo;

}

package io.github.mahjoubech.smartshop.mapper;

import io.github.mahjoubech.smartshop.dto.request.PaymentRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.PaymentResponseDetailDTO;
import io.github.mahjoubech.smartshop.model.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)

    Payment toEntity(PaymentRequestDTO payment);

    PaymentResponseDetailDTO toResponse(Payment payment);
}

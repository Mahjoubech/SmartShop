package io.github.mahjoubech.smartshop.mapper;

import io.github.mahjoubech.smartshop.dto.request.OrderItemRequestDTO;
import io.github.mahjoubech.smartshop.model.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem toEntity(OrderItemRequestDTO dto);
}

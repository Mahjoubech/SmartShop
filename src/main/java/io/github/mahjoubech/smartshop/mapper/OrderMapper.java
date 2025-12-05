package io.github.mahjoubech.smartshop.mapper;

import io.github.mahjoubech.smartshop.dto.request.OrderRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.basic.OrderResponseBasicAdminDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.OrderResponseDetailDTO;
import io.github.mahjoubech.smartshop.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(OrderRequestDTO dto);

    OrderResponseDetailDTO toResponseDetail(Order entity);
    OrderResponseBasicAdminDTO toResponseBasicAdmin(Order entity);
}

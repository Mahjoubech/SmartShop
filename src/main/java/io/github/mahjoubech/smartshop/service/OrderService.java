package io.github.mahjoubech.smartshop.service;

import io.github.mahjoubech.smartshop.dto.request.OrderRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.OrderResponseDetailDTO;

public interface OrderService {
    OrderResponseDetailDTO createOrder(OrderRequestDTO orderRequestDTO);
}

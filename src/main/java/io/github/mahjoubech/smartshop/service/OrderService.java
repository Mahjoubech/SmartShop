package io.github.mahjoubech.smartshop.service;

import io.github.mahjoubech.smartshop.dto.request.OrderRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.basic.OrderResponseBasicAdminDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.OrderResponseDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDetailDTO createOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDetailDTO updateOrder(String id, OrderRequestDTO orderRequestDTO);
    void deleteOrder(String orderId);
    OrderResponseDetailDTO getOrderById(String orderId);
    Page<OrderResponseBasicAdminDTO> getAllOrdersAdmin(Pageable pageable);
}

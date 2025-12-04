package io.github.mahjoubech.smartshop.service.impl;

import io.github.mahjoubech.smartshop.dto.request.OrderItemRequestDTO;
import io.github.mahjoubech.smartshop.dto.request.OrderRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.OrderResponseDetailDTO;
import io.github.mahjoubech.smartshop.exception.ResourceNotFoundException;
import io.github.mahjoubech.smartshop.mapper.OrderItemMapper;
import io.github.mahjoubech.smartshop.mapper.OrderMapper;
import io.github.mahjoubech.smartshop.model.entity.Client;
import io.github.mahjoubech.smartshop.model.entity.Order;
import io.github.mahjoubech.smartshop.model.entity.OrderItem;
import io.github.mahjoubech.smartshop.model.entity.Product;
import io.github.mahjoubech.smartshop.model.enums.OrderStatus;
import io.github.mahjoubech.smartshop.repository.ClientRepository;
import io.github.mahjoubech.smartshop.repository.OrderItemRepository;
import io.github.mahjoubech.smartshop.repository.OrderRepository;
import io.github.mahjoubech.smartshop.repository.ProductRepository;
import io.github.mahjoubech.smartshop.service.OrderService;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private  final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponseDetailDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Optional<Client> client = clientRepository.findById(orderRequestDTO.getClientId());
        if (client.isEmpty()) {
            throw new ResourceNotFoundException("Client not found with ID: " + orderRequestDTO.getClientId());
        }
        Order order = Order.builder()
                .client(client.get())
                .subTotal(BigDecimal.ZERO)
                .discount(BigDecimal.ZERO)
                .TVA(BigDecimal.ZERO)
                .totalTTC(BigDecimal.ZERO)
                .remainingAmount(BigDecimal.ZERO)
                .build();
       List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemRequestDTO itemRequestDTO : orderRequestDTO.getOrderItems()) {

            Optional<Product> product = productRepository.findById(itemRequestDTO.getProductId());
            if (product.isEmpty()) {
                throw new ResourceNotFoundException("Product not found with ID: " + itemRequestDTO.getProductId());
            }
  OrderItem item = OrderItem.builder()
          .order(order)
          .product(product.get())
          .quantity(itemRequestDTO.getQuantity())
          .unitPrice(product.get().getUnitPrice())
          .lineTotal(product.get().getUnitPrice().multiply(BigDecimal.valueOf(itemRequestDTO.getQuantity())))
          .build();
            orderItemList.add(item);
        }
        order.setOrderItems(orderItemList);

        BigDecimal subTotal = orderItemList.stream()
                        .map(OrderItem::getLineTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setSubTotal(subTotal);
        order.setTVA(subTotal.multiply(BigDecimal.valueOf(0.2)));
        order.setTotalTTC(order.getSubTotal().subtract(order.getDiscount()).add(order.getTVA()));
        order.setRemainingAmount(order.getTotalTTC());

        return orderMapper.toResponseDetail( orderRepository.save(order));
    }
    @Override
    @Transactional
    public OrderResponseDetailDTO updateOrder(String orderId, OrderRequestDTO orderRequestDTO) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        Client client = clientRepository.findById(orderRequestDTO.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client not found with ID: " + orderRequestDTO.getClientId()));

        order.setClient(client);

        // -------------------- DELETE OLD ITEMS --------------------
        orderItemRepository.deleteItemsByOrderId(orderId);

        order.getOrderItems().clear();

        // -------------------- ADD NEW ITEMS -----------------------
        for (OrderItemRequestDTO itemRequestDTO : orderRequestDTO.getOrderItems()) {

            Product product = productRepository.findById(itemRequestDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product not found with ID: " + itemRequestDTO.getProductId()));

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequestDTO.getQuantity())
                    .unitPrice(product.getUnitPrice())
                    .lineTotal(product.getUnitPrice()
                            .multiply(BigDecimal.valueOf(itemRequestDTO.getQuantity())))
                    .build();

            order.getOrderItems().add(item);
        }

        // recalculations
        BigDecimal subTotal = order.getOrderItems().stream()
                .map(OrderItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setSubTotal(subTotal);
        order.setTVA(subTotal.multiply(BigDecimal.valueOf(0.20)));
        order.setTotalTTC(order.getSubTotal().add(order.getTVA()));
        order.setRemainingAmount(order.getTotalTTC());

        return orderMapper.toResponseDetail(orderRepository.save(order));
    }
    @Override
    @Transactional
   public void deleteOrder(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with ID: " + orderId);
        }
        orderRepository.delete(order.get());
   }




}

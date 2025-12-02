package io.github.mahjoubech.smartshop.repository;

import io.github.mahjoubech.smartshop.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
}

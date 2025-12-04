package io.github.mahjoubech.smartshop.repository;

import io.github.mahjoubech.smartshop.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    @Modifying
    @Query("delete from OrderItem oi where oi.order.id = :orderId")
    void deleteItemsByOrderId(@Param("orderId") String orderId);
}

package io.github.mahjoubech.smartshop.repository;

import io.github.mahjoubech.smartshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
    boolean existsByCodePromo(String codePromo);
}

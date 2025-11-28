package io.github.mahjoubech.smartshop.repository;

import io.github.mahjoubech.smartshop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}

package io.github.mahjoubech.smartshop.repository;

import io.github.mahjoubech.smartshop.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE p.deleted = false")
    Page<Product> findAllActiveProducts(Pageable pageable);
    Optional<Product> findByIdAndDeletedFalse(String id);
}

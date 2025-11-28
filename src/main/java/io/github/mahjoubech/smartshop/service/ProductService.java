package io.github.mahjoubech.smartshop.service;

import io.github.mahjoubech.smartshop.dto.request.ProductRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.basic.ProductResponseBasicDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.ProductResponseDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponseDetailDTO createProduct(ProductRequestDTO productRequestDTO);
    Page<ProductResponseDetailDTO> getAllProductsDetail(Pageable pageable);
    ProductResponseDetailDTO updateProduct(String productId, ProductRequestDTO productRequestDTO);
    ProductResponseDetailDTO getProductById(String productId);
    void deleteProduct(String productId);
    Page<ProductResponseBasicDTO> getAllProducts(Pageable pageable);
}

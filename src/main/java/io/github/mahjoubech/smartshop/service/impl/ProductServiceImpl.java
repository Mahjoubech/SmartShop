package io.github.mahjoubech.smartshop.service.impl;

import io.github.mahjoubech.smartshop.dto.request.ProductRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.basic.ProductResponseBasicDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.ProductResponseDetailDTO;
import io.github.mahjoubech.smartshop.exception.ResourceNotFoundException;
import io.github.mahjoubech.smartshop.mapper.ProductMapper;
import io.github.mahjoubech.smartshop.model.entity.Product;
import io.github.mahjoubech.smartshop.repository.ProductRepository;
import io.github.mahjoubech.smartshop.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponseDetailDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        return productMapper.toResponseDetail(productRepository.save(product));
    }

    @Override
    public Page<ProductResponseDetailDTO> getAllProductsDetail(Pageable pageable) {
        return  productRepository.findAll(pageable)
                .map(productMapper::toResponseDetail);
    }

    @Override
    @Transactional
    public ProductResponseDetailDTO updateProduct(String id , ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        product.setProductName(productRequestDTO.getProductName());
        product.setUnitPrice(productRequestDTO.getUnitPrice());
        product.setQuantity(productRequestDTO.getQuantity());
        return productMapper.toResponseDetail(productRepository.save(product));
    }


    @Override
    public ProductResponseDetailDTO getProductById(String productId) {
        return productRepository.findById(productId)
                .map(productMapper::toResponseDetail)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
    }

    @Override
    public void deleteProduct(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with ID: " + productId);
        }
        productOptional.get().setDeleted(true);
        productRepository.save(productOptional.get());
    }

    @Override
    public Page<ProductResponseBasicDTO> getAllProducts(Pageable pageable) {
        return  productRepository.findAll(pageable)
                .map(productMapper::toResponseBasic);
    }
    @Override
    @Transactional
    public Page<ProductResponseDetailDTO> getAllActiveProducts(Pageable pageable) {
        return  productRepository.findAllActiveProducts(pageable)
                .map(productMapper::toResponseDetail);
    }
}

package io.github.mahjoubech.smartshop.service;

import io.github.mahjoubech.smartshop.dto.request.ProductRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.basic.ProductResponseBasicDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.ProductResponseDetailDTO;
import io.github.mahjoubech.smartshop.exception.ResourceNotFoundException;
import io.github.mahjoubech.smartshop.mapper.ProductMapper;
import io.github.mahjoubech.smartshop.mapper.ProductMapperImpl;
import io.github.mahjoubech.smartshop.model.entity.Product;
import io.github.mahjoubech.smartshop.repository.ProductRepository;
import io.github.mahjoubech.smartshop.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
@Mock
ProductRepository productRepository;
@Mock
ProductMapper productMapper;
@InjectMocks
ProductServiceImpl productServiceImpl;
Product  product;
@BeforeEach
    void setUp() {
    product = new Product();
    product.setId("1");
    product.setProductName("testProduct");
}
@Test
    void createProduct_shouldReturnDetailDTO(){
    //arrange
    ProductRequestDTO productRequestDTO = new ProductRequestDTO();
    Product product = new Product();
    Product saveProduct = new Product();
    ProductResponseDetailDTO  productResponseDetailDTO = new ProductResponseDetailDTO();

    when(productMapper.toEntity(productRequestDTO)).thenReturn(product);
    when(productRepository.save(product)).thenReturn(saveProduct);
    when(productMapper.toResponseDetail(saveProduct)).thenReturn(productResponseDetailDTO);
    //act
    ProductResponseDetailDTO result =  productServiceImpl.createProduct(productRequestDTO);
    //assert
    assertNotNull(result);
    verify(productRepository).save(product);
    verify(productMapper).toResponseDetail(saveProduct);
}
@Test
    void getAllProductsDetail_shouldReturnPage() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Product product = new Product();
        ProductResponseDetailDTO dto = new ProductResponseDetailDTO();

        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findAll(pageable)).thenReturn(productPage);
        when(productMapper.toResponseDetail(product)).thenReturn(dto);

        // Act
        Page<ProductResponseDetailDTO> result = productServiceImpl.getAllProductsDetail(pageable);

        // Assert
        assertEquals(1, result.getTotalElements());
        verify(productRepository).findAll(pageable);
    }
}

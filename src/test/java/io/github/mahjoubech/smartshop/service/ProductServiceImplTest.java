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
@Test
    void updateProduct_shouldUpdateAndReturnDTO() {
    // Arrange
    String productId = "123";
    ProductRequestDTO req = new ProductRequestDTO();
    req.setProductName("Updated");
    req.setUnitPrice(BigDecimal.TEN);
    req.setQuantity(5);

    Product product = new Product();
    Product updated = new Product();
    ProductResponseDetailDTO response = new ProductResponseDetailDTO();

    when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    when(productRepository.save(product)).thenReturn(updated);
    when(productMapper.toResponseDetail(updated)).thenReturn(response);

    // Act
    ProductResponseDetailDTO result = productServiceImpl.updateProduct(productId, req);

    // Assert
    assertNotNull(result);
    verify(productRepository).save(product);
}
@Test
    void updateProduct_shouldThrowException_WhenIdNotFound() {
        // Arrange
        String id = "notExist";
        ProductRequestDTO req = new ProductRequestDTO();

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Assert & Act
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.updateProduct(id, req));
    }
@Test
    void getProductById_shouldReturnProductDetailDTO() {
        // Arrange
        String id = "101";
        Product product = new Product();
        ProductResponseDetailDTO response = new ProductResponseDetailDTO();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productMapper.toResponseDetail(product)).thenReturn(response);

        // Act
        ProductResponseDetailDTO result = productServiceImpl.getProductById(id);

        // Assert
        assertNotNull(result);
    }
@Test
    void getProductById_shouldThrowException_WhenNotFound() {
        // Arrange
        String id = "400";
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProductById(id));
    }
@Test
    void deleteProduct_shouldMarkDeletedTrue() {
        // Arrange
        String id = "10";
        Product product = new Product();
        product.setDeleted(false);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        productServiceImpl.deleteProduct(id);

        // Assert
        assertTrue(product.getDeleted());
        verify(productRepository).save(product);
    }
 @Test
    void deleteProduct_shouldThrowException_WhenNotFound() {
        // Arrange
        String id = "notFound";
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.deleteProduct(id));
    }
}

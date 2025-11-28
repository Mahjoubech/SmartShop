package io.github.mahjoubech.smartshop.mapper;

import io.github.mahjoubech.smartshop.dto.request.ProductRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.basic.ProductResponseBasicDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.ProductResponseDetailDTO;
import io.github.mahjoubech.smartshop.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target="id" , ignore = true)
    Product toEntity(ProductRequestDTO dto);
    ProductResponseDetailDTO toResponseDetail(Product entity);
    ProductResponseBasicDTO toResponseBasic(Product entity);

}

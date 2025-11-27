package io.github.mahjoubech.smartshop.mapper;

import io.github.mahjoubech.smartshop.dto.request.ClientRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.basic.ClientResponseBasicDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.ClientResponseDetailDTO;
import io.github.mahjoubech.smartshop.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nomComplet", expression = "java(dto.getNom() + \" \" + dto.getPrenom())")
    @Mapping(target = "role", expression = "java(io.github.mahjoubech.smartshop.model.enums.Roles.CLIENT)")
    Client toEntity(ClientRequestDTO dto);

    ClientResponseDetailDTO toClientDetail(Client entity);

    ClientResponseBasicDTO toClientBasic(Client entity);
}


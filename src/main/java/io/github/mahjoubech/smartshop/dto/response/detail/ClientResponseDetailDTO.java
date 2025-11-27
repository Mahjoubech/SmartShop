package io.github.mahjoubech.smartshop.dto.response.detail;

import io.github.mahjoubech.smartshop.model.enums.CustomerTierStatus;
import io.github.mahjoubech.smartshop.model.enums.Roles;
import lombok.Data;

@Data
public class ClientResponseDetailDTO {
    private String id;
    private String userName;
    private String password;
    private String nomComplet;
    private String email;
    private String customerTier;
}

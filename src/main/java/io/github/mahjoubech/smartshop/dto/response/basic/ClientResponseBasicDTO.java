package io.github.mahjoubech.smartshop.dto.response.basic;

import lombok.Data;

@Data
public class ClientResponseBasicDTO {
    private String userName;
    private String nomComplet;
    private String email;
    private String customerTier;

}

package io.github.mahjoubech.smartshop.dto.response;

import io.github.mahjoubech.smartshop.model.enums.Roles;
import lombok.Data;

@Data
public class AuthResponseDTO {
    private String message;
    private String id;
    private String useName;
    private Roles role;
}

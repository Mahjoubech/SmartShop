package io.github.mahjoubech.smartshop.service;

import io.github.mahjoubech.smartshop.dto.request.LoginRequestDTO;
import io.github.mahjoubech.smartshop.model.entity.User;

public interface AuthService {
    User login(LoginRequestDTO loginRequestDTO);
}

package io.github.mahjoubech.smartshop.service.impl;

import io.github.mahjoubech.smartshop.dto.request.LoginRequestDTO;
import io.github.mahjoubech.smartshop.exception.InvalidCredentialsException;
import io.github.mahjoubech.smartshop.exception.ResourceNotFoundException;
import io.github.mahjoubech.smartshop.model.entity.User;
import io.github.mahjoubech.smartshop.repository.UserRepository;
import io.github.mahjoubech.smartshop.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User login(LoginRequestDTO loginRequestDTO){
        User user = userRepository.findByUserName(loginRequestDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("userName not found"));
        boolean passwordMatches = BCrypt.checkpw(loginRequestDTO.getPassword(),user.getPassword());
        if(!passwordMatches){
            throw new InvalidCredentialsException("Invalid username or password");
        }
        return user;
    }



}

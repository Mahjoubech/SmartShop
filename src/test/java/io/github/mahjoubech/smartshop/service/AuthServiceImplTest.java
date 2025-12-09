package io.github.mahjoubech.smartshop.service;

import io.github.mahjoubech.smartshop.dto.request.LoginRequestDTO;
import io.github.mahjoubech.smartshop.exception.InvalidCredentialsException;
import io.github.mahjoubech.smartshop.exception.ResourceNotFoundException;
import io.github.mahjoubech.smartshop.model.entity.Client;
import io.github.mahjoubech.smartshop.model.entity.User;
import io.github.mahjoubech.smartshop.repository.UserRepository;
import io.github.mahjoubech.smartshop.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest
{
@Mock
  UserRepository userRepository;
@InjectMocks
AuthServiceImpl authService;
  Client client;
@BeforeEach
void setUp() {
    String hach = BCrypt.hashpw("password", BCrypt.gensalt());
    client = new Client();
    client.setId("1");
    client.setUserName("testUser");
    client.setPassword(hach);
    }

 @Test
    void login_sholderReturnUser_WhenCredentialsAreCorrect(){
    //arrange
     LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password");
     when(userRepository.findByUserName(loginRequest.getUsername()))
             .thenReturn(Optional.of(client));
    //act
     User result = authService.login(loginRequest);
    //assert
     assertNotNull(result);
     assertEquals(client , result);
 }
 @Test
    void login_shouldThrowInvalidCredentialsException_WhenCredentialsAreIncorrect(){
    //arrange
     LoginRequestDTO loginRequest = new LoginRequestDTO();
     loginRequest.setUsername("mahjoubech");
     loginRequest.setPassword("password");
     when(userRepository.findByUserName(loginRequest.getUsername()))
                .thenReturn(Optional.empty());
     //act + asert
     assertThrows(ResourceNotFoundException.class,()->authService.login(loginRequest));
 }
 @Test
    void login_shouldThrowInvalidCredentialsException_WhenPasswordIsIncorrect(){
    //arrange
    LoginRequestDTO loginRequest = new LoginRequestDTO();
    loginRequest.setUsername("testUser");
    loginRequest.setPassword("wrongPassword");
    when(userRepository.findByUserName(loginRequest.getUsername())).thenReturn(Optional.of(client));
    //act+assert
     assertThrows(InvalidCredentialsException.class , ()->authService.login(loginRequest));
 }

}


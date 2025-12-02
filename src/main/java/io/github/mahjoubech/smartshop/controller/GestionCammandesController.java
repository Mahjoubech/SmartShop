package io.github.mahjoubech.smartshop.controller;

import io.github.mahjoubech.smartshop.dto.request.ClientRequestDTO;
import io.github.mahjoubech.smartshop.dto.request.OrderRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.ClientResponseDetailDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.OrderResponseDetailDTO;
import io.github.mahjoubech.smartshop.repository.ProductRepository;
import io.github.mahjoubech.smartshop.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class GestionCammandesController {
    private final OrderService orderService;
    @PostMapping("/admin/create")
    public ResponseEntity<OrderResponseDetailDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDetailDTO response = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PutMapping("/admin/update")
//    p
}

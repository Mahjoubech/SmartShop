package io.github.mahjoubech.smartshop.controller;

import io.github.mahjoubech.smartshop.dto.request.PaymentRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.PaymentResponseDetailDTO;
import io.github.mahjoubech.smartshop.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class GestionPaymentController {
    private final PaymentService paymentService;

    @PostMapping("/admin/create")
    public ResponseEntity<PaymentResponseDetailDTO> ceratePayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDetailDTO response = paymentService.createPayment(paymentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

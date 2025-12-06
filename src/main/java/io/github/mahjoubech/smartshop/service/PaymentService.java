package io.github.mahjoubech.smartshop.service;

import io.github.mahjoubech.smartshop.dto.request.PaymentRequestDTO;
import io.github.mahjoubech.smartshop.dto.request.PaymentRequestStatusDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.PaymentResponseDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    PaymentResponseDetailDTO createPayment(PaymentRequestDTO paymentRequestDTO);
    Page<PaymentResponseDetailDTO> getAllPayments(Pageable pageable);
    PaymentResponseDetailDTO getPaymentById(String id);
}

package io.github.mahjoubech.smartshop.service.impl;

import io.github.mahjoubech.smartshop.dto.request.PaymentRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.PaymentResponseDetailDTO;
import io.github.mahjoubech.smartshop.exception.InvalidCredentialsException;
import io.github.mahjoubech.smartshop.exception.ResourceNotFoundException;
import io.github.mahjoubech.smartshop.mapper.PaymentMapper;
import io.github.mahjoubech.smartshop.model.entity.Order;
import io.github.mahjoubech.smartshop.model.entity.Payment;
import io.github.mahjoubech.smartshop.model.enums.PayementType;
import io.github.mahjoubech.smartshop.model.enums.PaymentStatus;
import io.github.mahjoubech.smartshop.repository.OrderRepository;
import io.github.mahjoubech.smartshop.repository.PaymentRepository;
import io.github.mahjoubech.smartshop.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    public Integer getNextPaymentNumber(String orderId) {
        Integer last = paymentRepository.findLastNumberByOrder(orderId);
        return (last == null) ? 1 : last + 1;
    }

    @Override
    @Transactional
    public PaymentResponseDetailDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        Optional<Order> order = orderRepository.findById(paymentRequestDTO.getOrderId());
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order not found");
        }
        BigDecimal amount = paymentRequestDTO.getAmount();
        if(amount.compareTo(order.get().getRemainingAmount()) > 0) {
            throw new InvalidCredentialsException("Montant dépasse le restant dû");
        }
        if(paymentRequestDTO.getType() == PayementType.ESPECES && amount.compareTo(new BigDecimal("20000")) > 0) {
            throw new InvalidCredentialsException("Montant en espèces dépasse 20.000 DH");
        }
        Payment payment = new Payment();
        payment.setOrder(order.get());
        payment.setAmount(amount);
        payment.setType(paymentRequestDTO.getType());
        payment.setNumeroPayment(getNextPaymentNumber(order.get().getId()));
        switch(payment.getType()) {
            case ESPECES:
                payment.setStatus(PaymentStatus.CLEARED);
                payment.setDateDeposit(LocalDate.now());
                break;
            case CHEQUE:
            case VIREMENT:
                payment.setStatus(PaymentStatus.PENDING);
                payment.setDateDeposit(null);
                break;
            default:
                throw new InvalidCredentialsException("Invalid payment type");
        }
        order.get().setRemainingAmount(order.get().getRemainingAmount().subtract(amount));
        orderRepository.save(order.get());
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }


}

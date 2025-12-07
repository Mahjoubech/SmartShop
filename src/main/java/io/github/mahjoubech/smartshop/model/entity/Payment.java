package io.github.mahjoubech.smartshop.model.entity;

import io.github.mahjoubech.smartshop.model.enums.PayementType;
import io.github.mahjoubech.smartshop.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Payment extends BaseEntity {

    @Column(name = "numero_payment", nullable = false)
    private Integer numeroPayment;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Builder.Default
    @Column(name = "date_payment", nullable = false)
    private LocalDate datePayment = LocalDate.now();

    @Column(name = "date_deposit")
    private LocalDate dateDeposit;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PayementType type;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}


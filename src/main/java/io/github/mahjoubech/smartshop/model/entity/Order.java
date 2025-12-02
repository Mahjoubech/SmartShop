package io.github.mahjoubech.smartshop.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.mahjoubech.smartshop.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Order extends  BaseEntity {
    @Column(name = "date_Order", nullable = false)
    @JsonFormat(pattern = "dd MMMM yyyy ", locale = "fr")
    @Builder.Default
    private LocalDate date = LocalDate.now();
    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;
    @Column(name = "discount", nullable = false)
    private BigDecimal discount;
    @Column(name = "TVA", nullable = false)
    private BigDecimal TVA;
    @Column(name = "total_TTC", nullable = false)
    private BigDecimal totalTTC;
    @Column(name = "reamaining_amount", nullable = false)
    private BigDecimal remainingAmount;
    @Column(name = "code_promo" , nullable = true)
    private String codePromo;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private OrderStatus status = OrderStatus.PANDING;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
}

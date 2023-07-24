package com.order.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "payments_data")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne
    private Order order;

    @Column(name = "real_total_price")
    private BigDecimal realTotalPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
}

package com.order.service.entities;

import com.order.service.entities.additional.ProcessStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

    @OneToOne(mappedBy = "order")
    private OrderMetadata orderMetadata;

    @OneToOne(mappedBy = "order")
    private PaymentData paymentData;

    @OneToOne(mappedBy = "order")
    private PaymentData rejectOrderCause;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProcessStatus processStatus;
}

package com.order.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "reject_order_causes")
@NoArgsConstructor
@AllArgsConstructor
public class RejectOrderCause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne
    private Order order;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "message")
    private String message;

}

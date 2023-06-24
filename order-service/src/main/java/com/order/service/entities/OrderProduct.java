package com.order.service.entities;

import com.order.service.entities.additional.ProductProcessStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "order_products")
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "product_version_id")
    private UUID productVersionId;

    @Column(name = "status")
    private ProductProcessStatus status;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "count")
    private Integer count;
}

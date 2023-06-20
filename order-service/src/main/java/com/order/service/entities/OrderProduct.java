package com.order.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    private Long id;

    @ManyToOne
    private Order orderId;

    private String productId;

    private Integer count;
}

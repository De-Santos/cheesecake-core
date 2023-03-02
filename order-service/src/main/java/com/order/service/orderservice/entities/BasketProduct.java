package com.order.service.orderservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "basket_product")
public class BasketProduct {
    @Id
    private Long id;

    @ManyToOne
    private Order orderId;

    private String productId;

    private Integer count;
}

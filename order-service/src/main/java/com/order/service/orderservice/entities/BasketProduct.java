package com.order.service.orderservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "basket_product")
public class BasketProduct {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private String productId;

    private Integer count;
}

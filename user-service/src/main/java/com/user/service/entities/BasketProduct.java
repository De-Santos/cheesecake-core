package com.user.service.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "basket_product")
public final class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private String productId;

    private Integer count;
}

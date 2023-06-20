package com.order.service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_products")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

    @Column(name = "creation_toime")
    private Date creationTime;

    @Column(name = "real_total_price")
    private BigDecimal realTotalPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

}

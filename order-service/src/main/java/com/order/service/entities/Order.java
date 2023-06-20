package com.order.service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Long id;

    @MapsId
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private List<OrderProduct> orderProduct;

    private LocalDateTime createDate;

    private BigDecimal realTotalPrice;

    private BigDecimal totalPrice;

}

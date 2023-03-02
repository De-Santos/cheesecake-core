package com.order.service.orderservice.entities;

import jakarta.persistence.*;
import lombok.Data;

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

    private Float realTotalPrice;

    private Float totalPrice;

}

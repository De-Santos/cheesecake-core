package com.order.service.orderservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "basket")
public class Basket {
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket")
//    @JoinColumn(name = "basket", referencedColumnName = "id")
    private List<BasketProduct> productList;

}

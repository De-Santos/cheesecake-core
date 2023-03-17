package com.user.service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "basket")
public final class Basket {
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket", fetch = FetchType.LAZY)
//    @JoinColumn(name = "basket", referencedColumnName = "id")
    private List<BasketProduct> productList;

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                '}';
    }
}

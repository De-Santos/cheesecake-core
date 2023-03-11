package com.order.service.orderservice.dao;

import com.order.service.orderservice.entities.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
}

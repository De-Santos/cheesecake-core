package com.order.service.orderservice.dao;

import com.order.service.orderservice.entities.Basket;
import com.order.service.orderservice.entities.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    @Modifying
    @Query("delete from BasketProduct b where b.id=:id and b.productId=:productId")
    void deleteTest(@Param("id") Long id, @Param("productId") String productId);
    boolean existsBasketProductByBasketAndProductId(Basket basket, String productId);
}

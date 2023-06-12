package com.user.service.dao;


import com.user.service.entities.Basket;
import com.user.service.entities.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    @Modifying
    @Query("delete from BasketProduct b where b.id=:id and b.productId=:productId")
    void forceDelete(@Param("id") Long id, @Param("productId") String productId);

    @Modifying
    @Query("delete from BasketProduct b where b.basket=:basket")
    void forceDeleteProductsByBasketId(@Param("basket") Basket basket);

    boolean existsBasketProductByBasketAndProductId(Basket basket, String productId);
}

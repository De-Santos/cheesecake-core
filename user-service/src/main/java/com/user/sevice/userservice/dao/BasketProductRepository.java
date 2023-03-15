package com.user.sevice.userservice.dao;


import com.user.sevice.userservice.entities.Basket;
import com.user.sevice.userservice.entities.BasketProduct;
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

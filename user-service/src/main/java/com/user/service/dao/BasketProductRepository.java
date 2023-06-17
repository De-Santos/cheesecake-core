package com.user.service.dao;


import com.user.service.entities.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    @Modifying
    @Query("delete from BasketProduct b where b.basket.id=:id and b.productVersionId=:productId")
    void forceDelete(@Param("id") Long id, @Param("productId") UUID productId);
}

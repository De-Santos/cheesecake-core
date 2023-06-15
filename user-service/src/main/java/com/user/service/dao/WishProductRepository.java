package com.user.service.dao;

import com.user.service.entities.WishProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface WishProductRepository extends JpaRepository<WishProduct, Long> {
    @Modifying
    @Query("delete from WishProduct wp where wp.productVersionId=:pvi and wp.wishList.id=:wli")
    void deleteByWishListAndProductVersionId(@Param("pvi") UUID productVersionId, @Param("wli") Long wishListId);

}

package com.order.service.orderservice.dao;

import com.order.service.orderservice.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
}

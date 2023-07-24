package com.order.service.dao;

import com.order.service.entities.RejectOrderCause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectOrderCauseRepository extends JpaRepository<RejectOrderCause, Long> {

    @Query(value = "INSERT INTO reject_order_causes (order_id, user_id, message) VALUES (:id, :userId, :message) RETURNING *", nativeQuery = true)
    RejectOrderCause saveFrom(@Param("id") Long id, @Param("userId") Long userId, @Param("message") String message);
}

package com.order.service.dao;

import com.order.service.entities.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDataRepository extends JpaRepository<PaymentData, Long> {
}

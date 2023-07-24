package com.order.service.dao;

import com.order.service.entities.OrderMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMetadataRepository extends JpaRepository<OrderMetadata, Long> {
}

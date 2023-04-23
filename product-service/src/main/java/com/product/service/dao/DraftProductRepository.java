package com.product.service.dao;

import com.product.service.entity.DraftProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftProductRepository extends JpaRepository<DraftProduct, Long> {
    boolean existsByParentId(String parentId);
}

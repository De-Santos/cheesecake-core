package com.product.service.dao;

import com.product.service.entity.DraftProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DraftProductRepository extends JpaRepository<DraftProduct, Long> {
    boolean existsByParentId(String parentId);

    @Query("SELECT dp.id FROM DraftProduct dp")
    List<Long> getAllIds();
}

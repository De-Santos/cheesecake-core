package com.product.service.dao;

import com.product.service.entity.DraftProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DraftProductRepository extends JpaRepository<DraftProduct, Long> {
    @Deprecated(forRemoval = true)
    boolean existsByParentVersionId(UUID parentId);

    @Deprecated(forRemoval = true)
    @Query("SELECT dp.id FROM DraftProduct dp")
    List<Long> getAllIds();
}

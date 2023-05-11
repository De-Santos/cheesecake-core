package com.product.service.dao;

import com.product.service.entity.ArchiveProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArchiveProductRepository extends JpaRepository<ArchiveProduct, Long> {
    Optional<ArchiveProduct> findByVersionId(UUID versionId);

    boolean existsByVersionId(UUID versionId);
}

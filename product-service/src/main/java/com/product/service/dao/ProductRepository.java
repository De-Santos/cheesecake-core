package com.product.service.dao;

import com.product.service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByVersionId(UUID versionId);

    boolean existsProductByVersionIdAndActiveIsTrue(UUID versionId);

    boolean existsProductByName(String name);

    boolean existsByVersionId(UUID versionId);

    @Query(value = "SELECT version_id FROM products WHERE id = :id", nativeQuery = true)
    UUID getVersionIdById(@Param("id") Long id);
}

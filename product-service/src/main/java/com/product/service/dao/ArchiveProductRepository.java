package com.product.service.dao;

import com.product.service.entity.ArchiveProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArchiveProductRepository extends MongoRepository<ArchiveProduct, String> {
    Optional<ArchiveProduct> findByVersionId(String versionId);
    boolean existsByVersionId(String versionId);
}

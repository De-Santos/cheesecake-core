package com.product.service.productservice.dao;

import com.product.service.productservice.model.ArchiveProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArchiveProductRepository extends MongoRepository<ArchiveProduct, String> {
    Optional<ArchiveProduct> findByVersionId(String versionId);
}

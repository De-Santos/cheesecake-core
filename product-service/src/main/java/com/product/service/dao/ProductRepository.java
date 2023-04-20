package com.product.service.dao;

import com.product.service.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findProductByVersionId(String versionId);
    boolean existsProductByVersionIdAndActiveIsTrue(String versionId);
    boolean existsProductByName(String name);
    boolean existsByVersionId(String versionId);
}

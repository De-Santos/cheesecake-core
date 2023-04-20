package com.product.service.dao;

import com.product.service.entity.DraftProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftProductRepository extends MongoRepository<DraftProduct, String> {
    boolean existsByParentId(String parentId);
}

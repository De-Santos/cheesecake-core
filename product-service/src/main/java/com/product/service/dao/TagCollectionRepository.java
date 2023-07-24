package com.product.service.dao;

import com.product.service.entity.additional.tag.TagCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagCollectionRepository extends JpaRepository<TagCollection, Long> {
}

package com.product.service.dao;

import com.product.service.entity.additional.FileCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileCollectionRepository extends JpaRepository<FileCollection, Long> {
}

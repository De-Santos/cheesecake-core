package com.product.service.dao;

import com.product.service.entity.additional.FileCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileCollectionRepository extends JpaRepository<FileCollection, Long> {
    Optional<FileCollection> findFileCollectionByDraftProductId(Long draftProductId);
}

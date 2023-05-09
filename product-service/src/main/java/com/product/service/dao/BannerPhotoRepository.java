package com.product.service.dao;

import com.product.service.entity.additional.FileCollection;
import com.product.service.entity.additional.BannerPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BannerPhotoRepository extends JpaRepository<BannerPhoto, Long> {
    Optional<Long> findPhotoIdByFileCollectionAndPosition(FileCollection fileCollection, Integer position);
}

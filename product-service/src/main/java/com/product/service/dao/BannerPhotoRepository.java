package com.product.service.dao;

import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.FileCollection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BannerPhotoRepository extends JpaRepository<BannerPhoto, Long> {
    Optional<Long> findPhotoIdByFileCollectionAndPosition(FileCollection fileCollection, Integer position);

    // TODO: 5/11/2023 move to jdbc
    @Transactional
    @Modifying
    @Query(value =
            "INSERT INTO banner_photos (image, media_type, position, real_photo_name, file_collection_id) " +
                    "SELECT image, media_type, position, real_photo_name, :fileCollection " +
                    "FROM banner_photos " +
                    "WHERE id = :photoId ",
            nativeQuery = true)
    void duplicateById(@Param("photoId") Long photoId, @Param("fileCollection") Long fileCollectionId);
}

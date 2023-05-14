package com.product.service.dao;

import com.product.service.entity.additional.DescriptionPhoto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionPhotoRepository extends JpaRepository<DescriptionPhoto, Long> {

    // TODO: 5/11/2023 move to jdbc
    @Deprecated(forRemoval = true)
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO description_photos (image, media_type, real_photo_name, file_collection_id) " +
            "SELECT image, media_type, real_photo_name, :fileCollection " +
            "FROM description_photos " +
            "WHERE id = :photoId ",
            nativeQuery = true)
    void duplicateById(@Param("photoId") Long photoId, @Param("fileCollection") Long fileCollectionId);
}

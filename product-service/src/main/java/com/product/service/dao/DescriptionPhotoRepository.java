package com.product.service.dao;

import com.product.service.entity.additional.DescriptionPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionPhotoRepository extends JpaRepository<DescriptionPhoto, Long> {
}

package com.product.service.entity;

import com.product.service.entity.additional.FileCollection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "archive_products")
@NoArgsConstructor
@AllArgsConstructor
public final class ArchiveProduct {
    @Id
    @Column(name = "version_id")
    private UUID versionId;
    private Long actualProductId;
    @OneToOne(mappedBy = "archiveProduct")
    private FileCollection images;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
}

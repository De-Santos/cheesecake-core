package com.product.service.entity;

import com.product.service.entity.additional.FileCollection;
import com.product.service.entity.additional.tag.TagCollection;
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

    @Column(name = "actual_product_id")
    private Long actualProductId;

    @OneToOne(mappedBy = "archiveProduct")
    private FileCollection images;

    @OneToOne(mappedBy = "archiveProduct")
    private TagCollection tags;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "create_date")
    private LocalDateTime createDate;
}

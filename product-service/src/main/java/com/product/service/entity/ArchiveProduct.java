package com.product.service.entity;

import com.product.service.entity.additional.FileCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "archiveProduct")
@NoArgsConstructor
@AllArgsConstructor
public final class ArchiveProduct {
    @Id
    private UUID versionId;
    private Long actualProductId;
    @OneToOne(mappedBy = "archiveProduct")
    private FileCollection images;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
}

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
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public final class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID versionId;
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private FileCollection images;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal salePrice;
    private LocalDateTime createDate;
    private boolean active;
}

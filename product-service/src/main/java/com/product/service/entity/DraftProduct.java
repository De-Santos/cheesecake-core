package com.product.service.entity;

import com.product.service.entity.additional.FileCollection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "drafts")
@NoArgsConstructor
@AllArgsConstructor
public final class DraftProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    @OneToOne(mappedBy = "draftProduct", cascade = CascadeType.REMOVE)
    private FileCollection images;

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;

    public static DraftProduct create() {
        DraftProduct draft = new DraftProduct();
        draft.setCreateDate(LocalDateTime.now());
        return draft;
    }
}

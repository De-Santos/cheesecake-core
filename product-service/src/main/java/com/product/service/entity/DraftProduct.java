package com.product.service.entity;

import com.product.service.entity.additional.FileCollection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drafts")
public final class DraftProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    @OneToOne(mappedBy = "draftProduct")
    private FileCollection images;

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;

    @Deprecated(forRemoval = true)
    public static DraftProduct create() {
        DraftProduct draft = new DraftProduct();
//        draft.setImages(FileCollection.create());
        draft.setCreateDate(LocalDateTime.now());
        return draft;
    }
}

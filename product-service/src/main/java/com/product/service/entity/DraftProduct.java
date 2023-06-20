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
@Table(name = "drafts")
@NoArgsConstructor
@AllArgsConstructor
public final class DraftProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hash", unique = true)
    private UUID hash;

    private UUID parentVersionId;

    @OneToOne(mappedBy = "draftProduct", fetch = FetchType.EAGER)
    private FileCollection images;

    @OneToOne(mappedBy = "draftProduct", fetch = FetchType.EAGER)
    private TagCollection tags;


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

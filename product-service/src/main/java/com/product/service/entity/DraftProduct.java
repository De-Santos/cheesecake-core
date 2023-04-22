package com.product.service.entity;

import com.product.service.entity.additional.FileCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "draft")
public final class DraftProduct {
    @Id
    private String id;
    private String parentId;
    private FileCollection images;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;

    public static DraftProduct create() {
        DraftProduct draft = new DraftProduct();
        draft.setImages(FileCollection.create());
        draft.setCreateDate(LocalDateTime.now());
        return draft;
    }
}

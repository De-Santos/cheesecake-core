package com.product.service.entity;

import com.product.service.entity.additional.FileCollection;
import com.product.service.entity.additional.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "draft")
public class DraftProduct {
    @Id
    private String id;
    private FileCollection images;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
}

package com.product.service.entity.additional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    private Long id;

    @ManyToOne
    @JoinTable(
            name = "banner_photos",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "file_collection_id")
    )
    private FileCollection fileCollection;

    private Integer position;
    private String mediaType;
    private String realPhotoName;
    private Binary image;
}

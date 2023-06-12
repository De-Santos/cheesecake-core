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
@Table(name = "banner_photos")
public final class BannerPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "file_collection_id", referencedColumnName = "id")
    private FileCollection fileCollection;

    @Column(name = "position")
    private Integer position;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "real_photo_name")
    private String realPhotoName;

    @Column(name = "image")
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", position=" + position +
                ", mediaType='" + mediaType + '\'' +
                ", realPhotoName='" + realPhotoName + '\'' +
                '}';
    }
}
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
public class BannerPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_collection_id", referencedColumnName = "id")
    private FileCollection fileCollection;

    private Integer position;
    private String mediaType;
    private String realPhotoName;
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
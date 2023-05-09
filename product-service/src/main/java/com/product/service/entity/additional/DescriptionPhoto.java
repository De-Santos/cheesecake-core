package com.product.service.entity.additional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Entity
@Table(name = "description_photos")
public class DescriptionPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_collection_id", referencedColumnName = "id")
    private FileCollection fileCollection;

    private String mediaType;
    private String realPhotoName;
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", mediaType='" + mediaType + '\'' +
                ", realPhotoName='" + realPhotoName + '\'' +
                '}';
    }
}

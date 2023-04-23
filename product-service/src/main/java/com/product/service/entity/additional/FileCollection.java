package com.product.service.entity.additional;

import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Entity
@Table(name = "file_collection")
public class FileCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "banner_photos", joinColumns = @JoinColumn(name = "file_collection_id"))
    @Column(name = "photo_id")
    private List<Long> bannerPhotos;

    @OneToOne
    private Photo descriptionPhoto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "draft_id", referencedColumnName = "id")
    private DraftProduct draftProduct;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archive_id", referencedColumnName = "versionId")
    private ArchiveProduct archiveProduct;

    public static FileCollection create() {
        return FileCollection.builder()
                .bannerPhotos(new ArrayList<>())
                .descriptionPhoto(null)
                .build();
    }

    // FIXME: 4/23/2023
    public List<Photo> getAll() {
//        List<Photo> allPhotos = new ArrayList<>(bannerPhotos);
//        if (Objects.nonNull(descriptionPhoto)) allPhotos.add(descriptionPhoto);
//        return allPhotos;
        return null;
    }

    // FIXME: 4/22/2023
    public Photo getPhotoByHash(UUID hash) {
//        if (Objects.nonNull(descriptionPhoto) && descriptionPhoto.getHash().equals(hash)) return descriptionPhoto;
//        return bannerPhotos.stream()
//                .filter(photo -> Objects.equals(photo.getHash(), hash))
//                .findFirst()
//                .orElseThrow(() -> new FileNotFoundException("File not found by id: " + hash));
        return null;
    }

    // FIXME: 4/22/2023
    public Optional<Photo> getPhotoByOrder(Integer position) {
//        return bannerPhotos.stream()
//                .filter(photo -> Objects.equals(photo.getOrder(), position))
//                .findFirst();
        return null;
    }

    // FIXME: 4/22/2023
    public Photo remove(UUID hash) {
//        if (Objects.nonNull(descriptionPhoto) && descriptionPhoto.getHash().equals(hash)) {
//            Photo temp = descriptionPhoto;
//            descriptionPhoto = null;
//            return temp;
//        }
//        Photo photo = bannerPhotos.stream()
//                .filter(it -> it.getHash().equals(hash))
//                .findFirst()
//                .orElseThrow(() -> new FileNotFoundException("File not found by id: " + hash));
//        bannerPhotos.remove(photo);
//        return photo;
        return null;
    }
}


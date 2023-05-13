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

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Entity
@Table(name = "file_collection")
public final class FileCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "fileCollection", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BannerPhoto> bannerPhotos;

    @OneToOne(mappedBy = "fileCollection", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private DescriptionPhoto descriptionPhoto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "draft_id", referencedColumnName = "id")
    private DraftProduct draftProduct;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archive_id", referencedColumnName = "version_id")
    private ArchiveProduct archiveProduct;

    public static FileCollection create(Product product) {
        return FileCollection.builder()
                .product(product)
                .build();
    }

    public static FileCollection create(DraftProduct draftProduct) {
        return FileCollection.builder()
                .draftProduct(draftProduct)
                .build();
    }

    public static FileCollection create(ArchiveProduct archiveProduct) {
        return FileCollection.builder()
                .archiveProduct(archiveProduct)
                .build();
    }

    public FileCollection draftProduct(DraftProduct newDraftProduct) {
        draftProduct = newDraftProduct;
        product = null;
        archiveProduct = null;
        return this;
    }

    public FileCollection product(Product newProduct) {
        draftProduct = null;
        product = newProduct;
        archiveProduct = null;
        return this;
    }

    public FileCollection archiveProduct(ArchiveProduct newArchiveProduct) {
        draftProduct = null;
        product = null;
        archiveProduct = newArchiveProduct;
        return this;
    }

    @Override
    public String toString() {
        return "FileCollection{" +
                "id=" + id +
                ", bannerPhotos=" + bannerPhotos +
                ", descriptionPhoto=" + descriptionPhoto +
                '}';
    }
}


package com.product.service.utils.request.utils.duplicator;

import com.product.service.dao.DraftProductRepository;
import com.product.service.dao.FileCollectionRepository;
import com.product.service.dao.ProductRepository;
import com.product.service.dao.TagCollectionRepository;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.FileCollection;
import com.product.service.entity.additional.tag.Tag;
import com.product.service.entity.additional.tag.TagCollection;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EntityDuplicator {
    private final FileCollectionRepository fileCollectionRepository;
    private final TagCollectionRepository tagCollectionRepository;
    private final DraftProductRepository draftProductRepository;
    private final ProductRepository productRepository;
    private final JdbcAccelerator accelerator;
    private final DuplicateConverter duplicateConverter;

    @Transactional
    public DraftProduct copyToDraft(Product product) {
        DraftProduct draftProduct = draftProductRepository.save(duplicateConverter.convert(product));
        FileCollection newFileCollection = fileCollectionRepository.save(FileCollection.create(draftProduct));
        TagCollection newTagCollection = tagCollectionRepository.save(TagCollection.create(draftProduct));
        this.duplicateFileCollection(newFileCollection, product.getImages());
        this.duplicateTagCollection(newTagCollection, product.getTags());
        return draftProduct;
    }

    @Transactional
    public DraftProduct copyToDraft(ArchiveProduct archiveProduct) {
        DraftProduct draftProduct = draftProductRepository.save(duplicateConverter.convert(archiveProduct));
        FileCollection newFileCollection = fileCollectionRepository.save(FileCollection.create(draftProduct));
        TagCollection newTagCollection = tagCollectionRepository.save(TagCollection.create(draftProduct));
        this.duplicateFileCollection(newFileCollection, archiveProduct.getImages());
        this.duplicateTagCollection(newTagCollection, archiveProduct.getTags());
        return draftProduct;
    }

    public void copyToArchive(Product product) {
        UUID archiveProductId = accelerator.saveArchiveProduct(duplicateConverter.convertToArchive(product));
        accelerator.matchFileCollectionToArchiveProduct(product.getImages().getId(), archiveProductId);
        accelerator.matchTagCollectionToArchiveProduct(product.getTags().getId(), archiveProductId);
    }

    public Product productMergeUpdate(Product product, DraftProduct draftProduct) {
        Product newProduct = duplicateConverter.convertToProduct(product, draftProduct);
        Long updatedProductId = accelerator.updateProduct(duplicateConverter.convertToProduct(product, draftProduct));
        accelerator.matchFileCollectionToProduct(draftProduct.getImages().getId(), updatedProductId);
        accelerator.matchTagCollectionToProduct(draftProduct.getTags().getId(), newProduct.getVersionId());
        draftProductRepository.delete(draftProduct);
        return productRepository.findById(updatedProductId).orElseThrow();
    }

    private void duplicateFileCollection(FileCollection newFileCollection, FileCollection oldFileCollection) {
        List<BannerPhoto> bannerPhotos = oldFileCollection.getBannerPhotos();
        for (BannerPhoto photo : bannerPhotos) {
            accelerator.duplicateBannerPhotoById(photo.getId(), newFileCollection.getId());
        }
        if (Objects.isNull(oldFileCollection.getDescriptionPhoto())) return;
        accelerator.duplicateDescriptionPhotoById(oldFileCollection.getDescriptionPhoto().getId(),
                newFileCollection.getId());
    }

    private void duplicateTagCollection(TagCollection newTagCollection, TagCollection oldTagCollection) {
        for (Tag tag : oldTagCollection.getTags()) {
            accelerator.silentAddTagToTagCollection(tag.getId(), newTagCollection.getId());
        }
    }
}

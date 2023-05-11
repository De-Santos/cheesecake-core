package com.product.service.utils.request.utils.duplicator;

import com.product.service.dao.*;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.FileCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class EntityDuplicator {
    private final BannerPhotoRepository bannerPhotoRepository;
    private final DescriptionPhotoRepository descriptionPhotoRepository;
    private final FileCollectionRepository fileCollectionRepository;
    private final ArchiveProductRepository archiveProductRepository;
    private final DraftProductRepository draftProductRepository;
    private final DuplicateConvertor duplicateConvertor;
    private final ProductRepository productRepository;

    @Transactional
    public DraftProduct copyToDraft(Product product) {
        DraftProduct draftProduct = duplicateConvertor.convert(product);
        FileCollection newfileCollection = fileCollectionRepository.save(FileCollection.create(draftProduct));
        FileCollection obtainedFileCollection = product.getImages();
        this.fileCollectionDuplicator(newfileCollection, obtainedFileCollection);
        return draftProductRepository.save(draftProduct);
    }

    @Transactional
    public DraftProduct copyToDraft(ArchiveProduct archiveProduct) {
        DraftProduct draftProduct = duplicateConvertor.convert(archiveProduct);
        FileCollection newfileCollection = fileCollectionRepository.save(FileCollection.create(draftProduct));
        FileCollection obtainedFileCollection = archiveProduct.getImages();
        this.fileCollectionDuplicator(newfileCollection, obtainedFileCollection);
        return draftProductRepository.save(draftProduct);
    }

    @Transactional
    public ArchiveProduct copyToArchive(Product product) {
        ArchiveProduct archive = duplicateConvertor.convertToArchive(product);
        FileCollection newfileCollection = fileCollectionRepository.save(FileCollection.create(archive));
        FileCollection obtainedFileCollection = product.getImages();
        this.fileCollectionDuplicator(newfileCollection, obtainedFileCollection);
        return archiveProductRepository.save(archive);
    }

    private void fileCollectionDuplicator(FileCollection newFileCollection, FileCollection oldFileCollection) {
        List<BannerPhoto> bannerPhotos = oldFileCollection.getBannerPhotos();
        for (BannerPhoto photo : bannerPhotos) {
            bannerPhotoRepository.duplicateById(photo.getId(), newFileCollection.getId());
        }
        if (Objects.isNull(oldFileCollection.getDescriptionPhoto())) return;
        descriptionPhotoRepository.duplicateById(oldFileCollection.getDescriptionPhoto().getId(),
                newFileCollection.getId());
    }


}

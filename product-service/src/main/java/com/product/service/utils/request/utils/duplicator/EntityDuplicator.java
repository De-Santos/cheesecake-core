package com.product.service.utils.request.utils.duplicator;

import com.product.service.dao.ArchiveProductRepository;
import com.product.service.dao.DraftProductRepository;
import com.product.service.dao.FileCollectionRepository;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.FileCollection;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class EntityDuplicator {
    private final FileCollectionRepository fileCollectionRepository;
    private final ArchiveProductRepository archiveProductRepository;
    private final DraftProductRepository draftProductRepository;
    private final JdbcAccelerator accelerator;
    private final DuplicateConvertor duplicateConvertor;

    @Transactional
    public DraftProduct copyToDraft(Product product) {
        DraftProduct draftProduct = draftProductRepository.save(duplicateConvertor.convert(product));
        FileCollection newfileCollection = fileCollectionRepository.save(FileCollection.create(draftProduct));
        FileCollection obtainedFileCollection = product.getImages();
        this.fileCollectionDuplicator(newfileCollection, obtainedFileCollection);
        return draftProduct;
    }

    @Transactional
    public DraftProduct copyToDraft(ArchiveProduct archiveProduct) {
        DraftProduct draftProduct = draftProductRepository.save(duplicateConvertor.convert(archiveProduct));
        FileCollection newfileCollection = fileCollectionRepository.save(FileCollection.create(draftProduct));
        FileCollection obtainedFileCollection = archiveProduct.getImages();
        this.fileCollectionDuplicator(newfileCollection, obtainedFileCollection);
        return draftProduct;
    }

    @Transactional
    public void copyToArchive(Product product) {
        ArchiveProduct archive = archiveProductRepository.save(duplicateConvertor.convertToArchive(product));
        fileCollectionRepository.save(product.getImages().archiveProduct(archive));
    }

    private void fileCollectionDuplicator(FileCollection newFileCollection, FileCollection oldFileCollection) {
        List<BannerPhoto> bannerPhotos = oldFileCollection.getBannerPhotos();
        for (BannerPhoto photo : bannerPhotos) {
            accelerator.duplicateBannerPhotoById(photo.getId(), newFileCollection.getId());
        }
        if (Objects.isNull(oldFileCollection.getDescriptionPhoto())) return;
        accelerator.duplicateDescriptionPhotoById(oldFileCollection.getDescriptionPhoto().getId(),
                newFileCollection.getId());
    }

}

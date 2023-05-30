package com.product.service.utils.request;

import com.product.service.dao.FileCollectionRepository;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.entity.additional.FileCollection;
import com.product.service.exception.exceptions.file.collection.found.FileCollectionNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class FileCollectionRequestConstructor {
    private FileCollectionRepository fileCollectionRepository;

    public FileCollection create(Product product) {
        log.info("Creating FileCollection for Product by product id: {}", product.getId());
        return fileCollectionRepository.save(FileCollection.create(product));
    }

    public FileCollection create(DraftProduct draftProduct) {
        log.info("Creating FileCollection for DraftProduct by draftProduct id: {}", draftProduct.getId());
        return fileCollectionRepository.save(FileCollection.create(draftProduct));
    }

    public FileCollection create(ArchiveProduct archiveProduct) {
        log.info("Creating FileCollection for ArchiveProduct by archiveProduct versionId: {}", archiveProduct.getVersionId());
        return fileCollectionRepository.save(FileCollection.create(archiveProduct));
    }

    public FileCollection get(Long id) {
        log.info("Getting file collection from database by id: {}", id);
        return fileCollectionRepository.findById(id).orElseThrow(() -> FileCollectionNotFoundException.create(id));
    }
}

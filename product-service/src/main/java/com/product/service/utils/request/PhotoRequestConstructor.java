package com.product.service.utils.request;

import com.product.service.dao.BannerPhotoRepository;
import com.product.service.dao.DescriptionPhotoRepository;
import com.product.service.dao.FileCollectionRepository;
import com.product.service.dto.photo.PhotoResponse;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.DescriptionPhoto;
import com.product.service.entity.additional.FileCollection;
import com.product.service.exception.exceptions.file.collection.found.FileCollectionNotFoundException;
import com.product.service.exception.exceptions.file.photo.found.BannerPhotoNotFoundException;
import com.product.service.exception.exceptions.file.photo.found.DescriptionPhotoNotFoundException;
import com.product.service.utils.additional.FileCollectionChecker;
import com.product.service.utils.convertor.Convertor;
import com.product.service.utils.request.utils.FileCollectionUtils;
import com.product.service.utils.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class PhotoRequestConstructor {
    private final FileCollectionRepository fileCollectionRepository;
    private final FileCollectionChecker fileCollectionChecker;
    private final BannerPhotoRepository bannerPhotoRepository;
    private final DescriptionPhotoRepository descriptionPhotoRepository;
    private final Convertor convertor;

    public BannerPhoto getBannerPhoto(Long id) {
        log.info("Get photo by index: {}", id);
        return this.safeGetBannerPhoto(id);
    }

    public DescriptionPhoto getDescriptionPhoto(Long id) {
        log.info("Get photo by index: {}", id);
        return this.safeGetDescriptionPhoto(id);
    }

    public Long uploadFile(@NonNull MultipartFile file, Long draftId) {
        log.info("Upload file in database with name: {}", file.getOriginalFilename());
        Validator.validateObtainFile(file);
        FileCollection fileCollection = this.safeGetFileCollection(draftId);
        fileCollectionChecker.checkFileOrder(fileCollection);
        return this.addNewPhoto(file, fileCollection);
    }

    public Long uploadFile(@NonNull MultipartFile file, Long draftId, Integer position) {
        log.info("Upload file in database with name: {} in position: {}", file.getOriginalFilename(), position);
        FileCollection fileCollection = this.safeGetFileCollection(draftId);
        fileCollectionChecker.checkFileCollectionBounds(position);
        Optional<Long> photoId = bannerPhotoRepository.findPhotoIdByFileCollectionAndPosition(fileCollection, position);
        if (photoId.isEmpty()) return this.addNewPhoto(file, fileCollection);
        return this.updateExistingPhoto(file, photoId.get(), fileCollection);
    }

    public Long uploadDescriptionFile(@NonNull MultipartFile file, Long draftId) {
        log.info("Upload file in database with name: {}", file.getOriginalFilename());
        FileCollection fileCollection = this.safeGetFileCollection(draftId);
        return this.setDescriptionPhoto(file, fileCollection);
    }

    // FIXME: 4/22/2023
    public PhotoResponse removeBannerPhoto(Long id) {
        log.info("Remove banner photo by id: {}", id);
        BannerPhoto bannerPhoto = this.safeGetBannerPhoto(id);
        bannerPhotoRepository.deleteById(id);
        this.optimizeFileCollection(bannerPhoto.getFileCollection().getId());
        return convertor.convert(bannerPhoto);
    }

    // FIXME: 4/22/2023
    public PhotoResponse removeDescriptionPhoto(Long id) {
        log.info("Remove banner photo by id: {}", id);
        DescriptionPhoto descriptionPhoto = this.safeGetDescriptionPhoto(id);
        bannerPhotoRepository.deleteById(id);
        this.optimizeFileCollection(descriptionPhoto.getFileCollection().getId());
        return convertor.convert(descriptionPhoto);
    }

    // FIXME: 4/22/2023
    private Long addNewPhoto(MultipartFile file, FileCollection fileCollection) {
        BannerPhoto newBannerPhoto = convertor.bannerPhotoBuilder(file, fileCollection);
        BannerPhoto bannerPhoto = bannerPhotoRepository.save(newBannerPhoto);
        fileCollection.getBannerPhotos().add(bannerPhoto);
        fileCollectionRepository.save(fileCollection);
        return bannerPhoto.getId();
    }

    private Long setDescriptionPhoto(MultipartFile file, FileCollection fileCollection) {
        DescriptionPhoto descriptionPhoto = convertor.descriptionPhotoBuilder(file, fileCollection);
        if (Objects.nonNull(fileCollection.getDescriptionPhoto()))
            descriptionPhoto.setId(fileCollection.getDescriptionPhoto().getId());
        return descriptionPhotoRepository.save(descriptionPhoto).getId();
    }

    private Long updateExistingPhoto(MultipartFile file, Long existingPhotoId, FileCollection fileCollection) {
        BannerPhoto bannerPhoto = convertor.bannerPhotoBuilder(file, fileCollection);
        bannerPhoto.setId(existingPhotoId);
        BannerPhoto oldBannerPhoto = this.safeGetBannerPhoto(existingPhotoId);
        bannerPhoto.setPosition(oldBannerPhoto.getPosition());
        return bannerPhotoRepository.save(bannerPhoto).getId();
    }

    private void optimizeFileCollection(long id) {
        FileCollection fileCollection= this.safeGetFileCollection(id);
        fileCollectionRepository.save(FileCollectionUtils.positionNormalization(fileCollection));
    }
    private BannerPhoto safeGetBannerPhoto(Long id) {
        return bannerPhotoRepository.findById(id)
                .orElseThrow(() -> BannerPhotoNotFoundException.create(id));
    }

    private DescriptionPhoto safeGetDescriptionPhoto(Long id) {
        return descriptionPhotoRepository.findById(id)
                .orElseThrow(() -> DescriptionPhotoNotFoundException.create(id));
    }
    private FileCollection safeGetFileCollection(Long draftId) {
        return fileCollectionRepository.findFileCollectionByDraftProductId(draftId)
                .orElseThrow(() -> new FileCollectionNotFoundException("File collection not found by draft id: " + draftId));
    }

}

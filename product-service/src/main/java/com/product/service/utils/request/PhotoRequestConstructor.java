package com.product.service.utils.request;

import com.product.service.dao.BannerPhotoRepository;
import com.product.service.dao.DescriptionPhotoRepository;
import com.product.service.dao.FileCollectionRepository;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.DescriptionPhoto;
import com.product.service.entity.additional.FileCollection;
import com.product.service.exception.exceptions.file.collection.found.FileCollectionNotFoundException;
import com.product.service.exception.exceptions.file.photo.found.BannerPhotoNotFoundException;
import com.product.service.exception.exceptions.file.photo.found.DescriptionPhotoNotFoundException;
import com.product.service.utils.check.FileCollectionChecker;
import com.product.service.utils.converter.Converter;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import com.product.service.utils.request.utils.FileCollectionUtils;
import com.product.service.utils.validator.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
    private final JdbcAccelerator accelerator;
    private final Converter converter;

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
        FileValidator.validateObtainFile(file);
        FileCollection fileCollection = this.safeGetFileCollectionByDraftId(draftId);
        fileCollectionChecker.checkFileOrder(fileCollection);
        return this.addNewPhoto(file, fileCollection);
    }

    public Long uploadFile(@NonNull MultipartFile file, Long draftId, Integer position) {
        log.info("Upload file in database with name: {} in position: {}", file.getOriginalFilename(), position);
        FileCollection fileCollection = this.safeGetFileCollectionByDraftId(draftId);
        fileCollectionChecker.checkFileCollectionBounds(position);
        Optional<Long> photoId = bannerPhotoRepository.findPhotoIdByFileCollectionAndPosition(fileCollection, position);
        if (photoId.isEmpty()) return this.addNewPhoto(file, fileCollection);
        return this.updateExistingPhoto(file, photoId.get(), fileCollection);
    }

    public Long uploadDescriptionFile(@NonNull MultipartFile file, Long draftId) {
        log.info("Upload file in database with name: {}", file.getOriginalFilename());
        FileCollection fileCollection = this.safeGetFileCollectionByDraftId(draftId);
        return this.setDescriptionPhoto(file, fileCollection);
    }

    @Transactional
    public BannerPhoto removeBannerPhoto(Long id) {
        log.info("Remove banner photo by id: {}", id);
        BannerPhoto bannerPhoto = this.safeGetBannerPhoto(id);
        bannerPhotoRepository.deleteById(id);
        return bannerPhoto;
    }

    @Transactional
    public DescriptionPhoto removeDescriptionPhoto(Long id) {
        log.info("Remove description photo by id: {}", id);
        DescriptionPhoto descriptionPhoto = this.safeGetDescriptionPhoto(id);
        descriptionPhotoRepository.deleteById(id);
        return descriptionPhoto;
    }

    private Long addNewPhoto(MultipartFile file, FileCollection fileCollection) {
        BannerPhoto newBannerPhoto = converter.bannerPhotoBuilder(file, fileCollection);
        BannerPhoto bannerPhoto = accelerator.saveBannerPhoto(newBannerPhoto);
        return bannerPhoto.getId();
    }

    private Long setDescriptionPhoto(MultipartFile file, FileCollection fileCollection) {
        DescriptionPhoto descriptionPhoto = converter.descriptionPhotoBuilder(file, fileCollection);
        if (Objects.nonNull(fileCollection.getDescriptionPhoto()))
            descriptionPhoto.setId(fileCollection.getDescriptionPhoto().getId());
        return accelerator.saveDescriptionPhoto(descriptionPhoto).getId();
    }

    private Long updateExistingPhoto(MultipartFile file, Long existingPhotoId, FileCollection fileCollection) {
        BannerPhoto bannerPhoto = converter.bannerPhotoBuilder(file, fileCollection);
        bannerPhoto.setId(existingPhotoId);
        BannerPhoto oldBannerPhoto = this.safeGetBannerPhoto(existingPhotoId);
        bannerPhoto.setPosition(oldBannerPhoto.getPosition());
        return bannerPhotoRepository.save(bannerPhoto).getId();
    }

    public void fileCollectionNormalization(long draftId) {
        FileCollection fileCollection = this.safeGetFileCollectionByDraftId(draftId);
        FileCollectionUtils.positionNormalization(fileCollection);
        fileCollectionRepository.saveAndFlush(FileCollectionUtils.positionNormalization(fileCollection));
    }

    private BannerPhoto safeGetBannerPhoto(Long id) {
        return accelerator.findBannerPhoto(id)
                .orElseThrow(() -> BannerPhotoNotFoundException.create(id));
    }

    private DescriptionPhoto safeGetDescriptionPhoto(Long id) {
        return accelerator.findDescriptionPhoto(id)
                .orElseThrow(() -> DescriptionPhotoNotFoundException.create(id));
    }

    private FileCollection safeGetFileCollectionByDraftId(Long draftId) {
        return fileCollectionRepository.findFileCollectionByDraftProductId(draftId)
                .orElseThrow(() -> new FileCollectionNotFoundException("File collection not found by draft id: " + draftId));
    }

}

package com.product.service.utils.request;

import com.product.service.dao.DraftProductRepository;
import com.product.service.dto.photo.PhotoResponse;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.additional.Photo;
import com.product.service.utils.additional.FileChecker;
import com.product.service.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ua.cheesecake.dto.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class PhotoRequestConstructor {
    private static final String DRAFT_PRODUCT_NOT_FOUND_MESSAGE = "Draft product not found by id: ";
    private final DraftProductRepository draftProductRepository;
    private final FileChecker fileChecker;
    private final Convertor convertor;

    public Photo getPhoto(String draftId, UUID hash) {
        log.info("Get photo by index: {} index product: {}", hash, draftId);
        return this.getDraftById(draftId).getImages().getPhotoByHash(hash);
    }

    public String uploadFile(@NonNull MultipartFile file, String draftId) {
        log.info("Upload file in database with name: {}", file.getOriginalFilename());
        DraftProduct draftProduct = this.getDraftById(draftId);
        fileChecker.checkFileOrder(draftProduct);
        return this.addNewPhoto(file, draftProduct);
    }

    public String uploadFile(@NonNull MultipartFile file, String draftId, Integer position) {
        log.info("Upload file in database with name: {} in position: {}", file.getOriginalFilename(), position);
        DraftProduct draftProduct = this.getDraftById(draftId);
        fileChecker.checkFileOrder(position);
        Optional<Photo> photo = draftProduct.getImages().getPhotoByOrder(position);
        if (photo.isEmpty()) return this.addNewPhoto(file, draftProduct);
        return updateExistingPhoto(file, photo.get(), draftProduct);
    }

    public String uploadDescriptionFile(@NonNull MultipartFile file, String draftId) {
        log.info("Upload file in database with name: {}", file.getOriginalFilename());
        DraftProduct draftProduct = this.getDraftById(draftId);
        return this.setNewDescriptionPhoto(file, draftProduct);
    }

    public PhotoResponse removeFile(String draftId, UUID id) {
        log.info("Remove file by id: {}", id);
        DraftProduct draftProduct = this.getDraftById(draftId);
        Photo photo = draftProduct.getImages().remove(id);
        draftProductRepository.save(draftProduct);
        return convertor.convert(photo);
    }

    private String addNewPhoto(MultipartFile file, DraftProduct draftProduct) {
        List<Photo> photoList = draftProduct.getImages().getBannerPhotos();
        Photo newPhoto = convertor.photoBuilder(file, photoList.size() + 1);
        photoList.add(newPhoto);
        draftProductRepository.save(draftProduct);
        return newPhoto.getHash().toString();
    }

    private String setNewDescriptionPhoto(MultipartFile file, DraftProduct draftProduct) {
        Photo newPhoto = convertor.photoBuilder(file, -1);
        draftProduct.getImages().setDescriptionPhoto(newPhoto);
        draftProductRepository.save(draftProduct);
        return newPhoto.getHash().toString();
    }

    private String updateExistingPhoto(MultipartFile file, Photo existingPhoto, DraftProduct draftProduct) {
        List<Photo> photoList = draftProduct.getImages().getBannerPhotos();
        Photo newPhoto = convertor.photoBuilder(file, existingPhoto.getOrder());
        photoList.remove(existingPhoto);
        photoList.add(newPhoto);
        draftProductRepository.save(draftProduct);
        return newPhoto.getHash().toString();
    }

    // TODO: 4/17/2023 refactor me please, better way make sub class we you make save method
    private DraftProduct getDraftById(String draftId) {
        return draftProductRepository.findById(draftId)
                .orElseThrow(() -> new ProductNotFoundException(DRAFT_PRODUCT_NOT_FOUND_MESSAGE + draftId));
    }
}

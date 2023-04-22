package com.product.service.utils.convertor;

import com.product.service.dto.photo.DraftProductDto;
import com.product.service.dto.photo.PhotoResponse;
import com.product.service.dto.photo.additional.FileCollectionDto;
import com.product.service.dto.photo.additional.PhotoDto;
import com.product.service.dto.product.ProductResponse;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.entity.additional.FileCollection;
import com.product.service.entity.additional.Photo;
import com.product.service.exception.exceptions.photo.invalid.InvalidFileException;
import com.product.service.exception.exceptions.product.modifying.FileCollectionModifyingException;
import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ua.cheesecake.dto.additional.TimeMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Convertor {
    private final TimeMapper timeMapper;

    public ProductResponse convert(Product product) {
        return ProductResponse.builder()
                .versionId(product.getVersionId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sailPrice(product.getSalePrice())
                .createDate(timeMapper.toTime(product.getCreateDate()))
                .active(product.isActive())
                .build();
    }

    public ProductResponse convert(ArchiveProduct archiveProduct) {
        return ProductResponse.builder()
                .versionId(archiveProduct.getVersionId())
                .name(archiveProduct.getName())
                .description(archiveProduct.getDescription())
                .price(archiveProduct.getPrice())
                .createDate(timeMapper.toTime(archiveProduct.getCreateDate()))
                .activeVersionId(archiveProduct.getActualProductId())
                .build();
    }

    public DraftProductDto convert(DraftProduct draftProduct) {
        return DraftProductDto.builder()
                .id(draftProduct.getId())
                .images(this.fileCollectionConvert(draftProduct.getImages()))
                .name(draftProduct.getName())
                .description(draftProduct.getDescription())
                .price(draftProduct.getPrice())
                .createDate(draftProduct.getCreateDate())
                .build();
    }

    private FileCollectionDto fileCollectionConvert(FileCollection fileCollection) {
        return FileCollectionDto.builder()
                .bannerPhotos(this.photoListCovert(fileCollection.getBannerPhotos()))
                .descriptionPhoto(this.photoConvert(fileCollection.getDescriptionPhoto()))
                .build();
    }

    private List<PhotoDto> photoListCovert(List<Photo> photoList) {
        if (Objects.isNull(photoList)) return Collections.emptyList();
        return photoList.stream()
                .map(this::photoConvert)
                .toList();
    }

    private PhotoDto photoConvert(Photo photo) {
        if (Objects.isNull(photo)) return null;
        return PhotoDto.builder()
                .hash(photo.getHash())
                .order(photo.getOrder())
                .realPhotoName(photo.getRealPhotoName())
                .build();
    }

    public Product convertToProduct(DraftProduct draftProduct) {
        return Product.builder()
                .images(draftProduct.getImages())
                .name(draftProduct.getName())
                .description(draftProduct.getDescription())
                .price(draftProduct.getPrice())
                .createDate(LocalDateTime.now())
                .build();
    }

    public ArchiveProduct convertToArchive(Product product) {
        return ArchiveProduct.builder()
                .versionId(product.getVersionId())
                .actualProductId(product.getId())
                .images(product.getImages())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(product.getCreateDate())
                .build();
    }

    public Product updateConvert(Product product, DraftProduct draftProduct) {
        product.setVersionId(UUID.randomUUID().toString());
        product.setImages(draftProduct.getImages());
        product.setName(draftProduct.getName());
        product.setDescription(draftProduct.getDescription());
        product.setPrice(draftProduct.getPrice());
        product.setCreateDate(LocalDateTime.now());
        product.setActive(product.isActive());
        return product;
    }

    public PhotoResponse convert(Photo photo) {
        return this.createPhotoResponse(photo);
    }

    public ResponseEntity<byte[]> mergeToPhotoResponse(Photo photo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getMediaType()));
        return new ResponseEntity<>(photo.getImage().getData(), headers, HttpStatus.OK);
    }

    private PhotoResponse createPhotoResponse(Photo photo) {
        return PhotoResponse.builder()
                .realPhotoName(photo.getRealPhotoName())
                .build();
    }

    public Photo photoBuilder(MultipartFile file, Integer order) {
        return Photo.builder()
                .hash(UUID.randomUUID())
                .order(order)
                .mediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())).toString())
                .realPhotoName(file.getOriginalFilename())
                .image(this.getBinaryFromFile(file))
                .build();
    }

    private Binary getBinaryFromFile(MultipartFile file) {
        try {
            return new Binary(file.getBytes());
        } catch (IOException e) {
            throw new InvalidFileException(e);
        }
    }

    public DraftProduct toDraft(Product product) {
        return DraftProduct.builder()
                .parentId(product.getVersionId())
                .images(product.getImages())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(LocalDateTime.now())
                .build();
    }

    public DraftProduct toDraft(ArchiveProduct archiveProduct) {
        return DraftProduct.builder()
                .parentId(archiveProduct.getVersionId())
                .images(archiveProduct.getImages())
                .name(archiveProduct.getName())
                .description(archiveProduct.getDescription())
                .price(archiveProduct.getPrice())
                .createDate(LocalDateTime.now())
                .build();
    }

    public DraftProduct updateConvert(DraftProduct draftProduct, DraftProductDto draftProductDto) {
        draftProduct.setImages(fileCollectionUpdate(draftProduct.getImages(), draftProductDto.getImages()));
        draftProduct.setName(draftProductDto.getName());
        draftProduct.setDescription(draftProductDto.getDescription());
        draftProduct.setPrice(draftProductDto.getPrice());
        return draftProduct;
    }

    private FileCollection fileCollectionUpdate(FileCollection fileCollection, FileCollectionDto fileCollectionDto) {
        Map<UUID, Photo> map = fileCollection.getBannerPhotos().stream()
                .collect(Collectors.toMap(Photo::getHash, Function.identity()));
        fileCollectionDto.getBannerPhotos()
                .forEach(
                        it -> {
                            Photo photo = map.get(it.getHash());
                            if (Objects.nonNull(photo)) {
                                photo.setOrder(it.getOrder());
                                map.put(it.getHash(), photo);
                            } else
                                throw new FileCollectionModifyingException("Unknown product in supplied draftProductDto, productId: " + it.getHash());
                        }
                );
        fileCollection.setBannerPhotos(new ArrayList<>(map.values()));
        return fileCollection;
    }
}


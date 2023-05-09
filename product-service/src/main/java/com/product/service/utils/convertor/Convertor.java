package com.product.service.utils.convertor;

import com.product.service.dto.photo.DraftProductDto;
import com.product.service.dto.photo.PhotoResponse;
import com.product.service.dto.photo.additional.FileCollectionDto;
import com.product.service.dto.photo.additional.PhotoDto;
import com.product.service.dto.product.ProductResponse;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.DescriptionPhoto;
import com.product.service.entity.additional.FileCollection;
import com.product.service.exception.exceptions.file.photo.invalid.InvalidFileException;
import com.product.service.exception.exceptions.product.modifying.FileCollectionModifyingException;
import com.product.service.utils.protector.Protector;
import lombok.RequiredArgsConstructor;
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

// FIXME: 4/22/2023
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

    // FIXME: 4/23/2023 
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
                .createDate(timeMapper.toTime(draftProduct.getCreateDate()))
                .build();
    }

    private FileCollectionDto fileCollectionConvert(FileCollection fileCollection) {
        return FileCollectionDto.builder()
                .bannerPhotos(this.bannerPhotoCovert(fileCollection.getBannerPhotos()))
                .descriptionPhoto(this.descriptionPhotoConvert(fileCollection.getDescriptionPhoto()))
                .build();
    }

    private List<PhotoDto> bannerPhotoCovert(List<BannerPhoto> bannerPhotoList) {
        if (Objects.isNull(bannerPhotoList)) return Collections.emptyList();
        return bannerPhotoList.stream()
                .map(this::photoConvert)
                .toList();
    }

    private PhotoDto descriptionPhotoConvert(DescriptionPhoto descriptionPhoto) {
        if (Objects.isNull(descriptionPhoto)) return null;
        return PhotoDto.builder()
                .id(descriptionPhoto.getId())
                .position(-1)
                .realPhotoName(descriptionPhoto.getRealPhotoName())
                .build();
    }

    private PhotoDto photoConvert(BannerPhoto bannerPhoto) {
        Protector.notNullRequired(bannerPhoto);
        return PhotoDto.builder()
                .id(bannerPhoto.getId())
                .position(bannerPhoto.getPosition())
                .realPhotoName(bannerPhoto.getRealPhotoName())
                .build();
    }

    public Product convertToProduct(DraftProduct draftProduct) {
        return Product.builder()
//                .images(draftProduct.getImages())
                .name(draftProduct.getName())
                .description(draftProduct.getDescription())
                .price(draftProduct.getPrice())
                .createDate(LocalDateTime.now())
                .build();
    }

    // FIXME: 4/23/2023 
    public ArchiveProduct convertToArchive(Product product) {
        return ArchiveProduct.builder()
//                .versionId(product.getVersionId())
                .actualProductId(product.getId())
                .images(product.getImages())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(product.getCreateDate())
                .build();
    }

    public Product updateConvert(Product product, DraftProduct draftProduct) {
        product.setVersionId(UUID.randomUUID());
//        product.setImages(draftProduct.getImages());
        product.setName(draftProduct.getName());
        product.setDescription(draftProduct.getDescription());
        product.setPrice(draftProduct.getPrice());
        product.setCreateDate(LocalDateTime.now());
        product.setActive(product.isActive());
        return product;
    }

    public PhotoResponse convert(BannerPhoto bannerPhoto) {
        return this.createBannerPhotoResponse(bannerPhoto);
    }
    public PhotoResponse convert(DescriptionPhoto descriptionPhoto) {
        return this.createDescriptionPhotoResponse(descriptionPhoto);
    }

    public ResponseEntity<byte[]> mergeToPhotoResponse(BannerPhoto bannerPhoto) {
        return this.responseBuilder(bannerPhoto.getImage(), bannerPhoto.getMediaType());
    }

    public ResponseEntity<byte[]> mergeToPhotoResponse(DescriptionPhoto bannerPhoto) {
        return this.responseBuilder(bannerPhoto.getImage(), bannerPhoto.getMediaType());
    }

    private ResponseEntity<byte[]> responseBuilder(byte[] image, String mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(mediaType));
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    private PhotoResponse createBannerPhotoResponse(BannerPhoto bannerPhoto) {
        return PhotoResponse.builder()
                .id(bannerPhoto.getId())
                .realPhotoName(bannerPhoto.getRealPhotoName())
                .build();
    }

    private PhotoResponse createDescriptionPhotoResponse(DescriptionPhoto descriptionPhoto) {
        return PhotoResponse.builder()
                .id(descriptionPhoto.getId())
                .realPhotoName(descriptionPhoto.getRealPhotoName())
                .build();
    }

    public BannerPhoto bannerPhotoBuilder(MultipartFile file, FileCollection filecollection) {
        return BannerPhoto.builder()
                .fileCollection(filecollection)
                .position(filecollection.getBannerPhotos().size() + 1)
                .mediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())).toString())
                .realPhotoName(file.getOriginalFilename())
                .image(this.getBinaryFromFile(file))
                .build();
    }

    public DescriptionPhoto descriptionPhotoBuilder(MultipartFile file, FileCollection filecollection) {
        return DescriptionPhoto.builder()
                .fileCollection(filecollection)
                .mediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())).toString())
                .realPhotoName(file.getOriginalFilename())
                .image(this.getBinaryFromFile(file))
                .build();
    }

    private byte[] getBinaryFromFile(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new InvalidFileException(e);
        }
    }

    public DraftProduct toDraft(Product product) {
        return DraftProduct.builder()
                .parentId(product.getId())
//                .images(product.getImages())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(LocalDateTime.now())
                .build();
    }

    // FIXME: 4/23/2023
    public DraftProduct toDraft(ArchiveProduct archiveProduct) {
        return DraftProduct.builder()
//                .parentId(archiveProduct.getVersionId())
//                .images(archiveProduct.getImages())
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

    // FIXME: 4/23/2023
    private FileCollection fileCollectionUpdate(FileCollection fileCollection, FileCollectionDto fileCollectionDto) {
        Map<Long, BannerPhoto> map = fileCollection.getBannerPhotos().stream()
                .collect(Collectors.toMap(BannerPhoto::getId, Function.identity()));
        fileCollectionDto.getBannerPhotos()
                .forEach(
                        it -> {
                            BannerPhoto bannerPhoto = map.get(it.getId());
                            if (Objects.nonNull(bannerPhoto)) {
                                bannerPhoto.setPosition(it.getPosition());
                                map.put(it.getId(), bannerPhoto);
                            } else
                                throw new FileCollectionModifyingException("Unknown product in supplied draftProductDto, productId: " + it.getId());
                        }
                );
        fileCollection.setBannerPhotos(new ArrayList<>(map.values()));
        return fileCollection;
    }
}


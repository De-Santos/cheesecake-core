package com.product.service.utils.converter;

import com.product.service.dto.photo.PhotoResponse;
import com.product.service.dto.photo.additional.FileCollectionDto;
import com.product.service.dto.photo.additional.PhotoDto;
import com.product.service.dto.product.DraftProductRequest;
import com.product.service.dto.product.DraftProductResponse;
import com.product.service.dto.product.ProductResponse;
import com.product.service.dto.tag.TagResponse;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.DescriptionPhoto;
import com.product.service.entity.additional.FileCollection;
import com.product.service.entity.additional.tag.Tag;
import com.product.service.entity.additional.tag.TagCollection;
import com.product.service.exception.exceptions.file.photo.invalid.InvalidFileException;
import com.product.service.exception.exceptions.product.modifying.FileCollectionModifyingException;
import com.product.service.utils.protector.Protector;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ua.cheesecake.dto.TimeMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Converter {
    private final TimeMapper timeMapper;
    private final JdbcAccelerator accelerator;

    public ProductResponse convert(Product product) {
        return ProductResponse.builder()
                .versionId(product.getVersionId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sailPrice(product.getSalePrice())
                .createDate(timeMapper.toTime(product.getCreateDate()))
                .active(product.isActive())
                .images(this.fileCollectionConvert(product.getImages()))
                .tags(this.tagCollectionConvert(product.getTags()))
                .build();
    }

    public ProductResponse convert(ArchiveProduct archiveProduct, UUID actualVersionId) {
        return ProductResponse.builder()
                .versionId(archiveProduct.getVersionId())
                .name(archiveProduct.getName())
                .description(archiveProduct.getDescription())
                .price(archiveProduct.getPrice())
                .createDate(timeMapper.toTime(archiveProduct.getCreateDate()))
                .activeVersionId(actualVersionId)
                .images(this.fileCollectionConvert(archiveProduct.getImages()))
                .tags(this.tagCollectionConvert(archiveProduct.getTags()))
                .build();
    }

    public DraftProductResponse convert(DraftProduct draftProduct) {
        return DraftProductResponse.builder()
                .id(draftProduct.getId())
                .images(this.fileCollectionConvert(draftProduct.getImages()))
                .tags(this.tagCollectionConvert(draftProduct.getTags()))
                .name(draftProduct.getName())
                .description(draftProduct.getDescription())
                .price(draftProduct.getPrice())
                .createDate(timeMapper.toTime(draftProduct.getCreateDate()))
                .build();
    }

    private List<TagResponse> tagCollectionConvert(TagCollection tagCollection) {
        if (Objects.isNull(tagCollection.getTags())) return Collections.emptyList();
        return tagCollection.getTags().stream()
                .map(this::tagConvert)
                .toList();
    }

    private TagResponse tagConvert(Tag tag) {
        return TagResponse.builder()
                .id(tag.getId())
                .tagName(tag.getTagName())
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
                .versionId(UUID.randomUUID())
                .images(draftProduct.getImages())
                .name(draftProduct.getName())
                .description(draftProduct.getDescription())
                .price(draftProduct.getPrice())
                .createDate(LocalDateTime.now())
                .build();
    }

    public ProductResponse convert(ArchiveProduct archiveProduct) {
        return ProductResponse.builder()
                .versionId(archiveProduct.getVersionId())
                .activeVersionId(accelerator.getProductVersionIdById(archiveProduct.getActualProductId()))
                .name(archiveProduct.getName())
                .description(archiveProduct.getDescription())
                .price(archiveProduct.getPrice())
                .createDate(timeMapper.toTime(archiveProduct.getCreateDate()))
                .active(false)
                .build();

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

    public DraftProduct updateConvert(DraftProduct draftProduct, DraftProductRequest draftProductRequest) {
        draftProduct.setImages(fileCollectionUpdate(draftProduct.getImages(), draftProductRequest.getImages()));
        draftProduct.setName(draftProductRequest.getName());
        draftProduct.setDescription(draftProductRequest.getDescription());
        draftProduct.setPrice(draftProductRequest.getPrice());
        return draftProduct;
    }

    private FileCollection fileCollectionUpdate(FileCollection fileCollection, FileCollectionDto fileCollectionDto) {
        if (Protector.anyNull(fileCollection, fileCollection.getBannerPhotos())) return fileCollection;
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

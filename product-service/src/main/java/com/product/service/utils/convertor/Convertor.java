package com.product.service.utils.convertor;

import com.product.service.dto.photo.PhotoResponse;
import com.product.service.dto.product.ProductRequest;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.Photo;
import com.product.service.entity.Product;
import com.product.service.exception.exceptions.photo.invalid.InvalidFileException;
import com.product.service.utils.template.Template;
import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ua.cheesecake.dto.additional.TimeMapper;
import ua.cheesecake.dto.product.ProductResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Convertor {
    private final Template template;
    private final TimeMapper timeMapper;

    public Product convert(ProductRequest productRequest) {
        return Product.builder()
                .versionId(UUID.randomUUID().toString())
                .imagesId(productRequest.getImagesId())
                .descriptionImageId(productRequest.getDescriptionImageId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .createDate(LocalDateTime.now())
                .active(productRequest.isActive())
                .build();
    }

    public ProductResponse convert(Product product) {
        return ProductResponse.builder()
                .versionId(product.getVersionId())
                .imagesId(product.getImagesId())
                .descriptionImageId(product.getDescriptionImageId())
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
                .imagesId(archiveProduct.getImagesId())
                .descriptionImageId(archiveProduct.getDescriptionImageId())
                .name(archiveProduct.getName())
                .description(archiveProduct.getDescription())
                .price(archiveProduct.getPrice())
                .createDate(timeMapper.toTime(archiveProduct.getCreateDate()))
                .activeVersionId(archiveProduct.getActualProductId())
                .build();
    }

    public ArchiveProduct convertToArchive(Product product) {
        return ArchiveProduct.builder()
                .versionId(product.getVersionId())
                .actualProductId(product.getId())
                .imagesId(product.getImagesId())
                .descriptionImageId(product.getDescriptionImageId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(product.getCreateDate())
                .build();
    }

    public Product updateConvert(Product product, ProductRequest productRequest) {
        product.setVersionId(UUID.randomUUID().toString());
        product.setImagesId(productRequest.getImagesId());
        product.setDescriptionImageId(productRequest.getDescriptionImageId());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCreateDate(LocalDateTime.now());
        product.setActive(product.isActive());
        return product;
    }

    public void convertToFileInUse(Product product) {
        List<String> photos = new ArrayList<>(product.getImagesId());
        photos.add(product.getDescriptionImageId());
        template.makeFilesInUse(photos);
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
                .id(photo.getId())
                .realPhotoName(photo.getRealPhotoName())
                .photo(photo.getImage().getData())
                .build();
    }

    public Photo photoBuilder(MultipartFile file) {
        return Photo.builder()
                .inUse(Boolean.FALSE)
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
}

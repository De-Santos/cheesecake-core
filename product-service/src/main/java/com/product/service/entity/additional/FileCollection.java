package com.product.service.entity.additional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.product.service.exception.exceptions.photo.found.FileNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class FileCollection {
    private List<Photo> bannerPhotos;
    // TODO: 4/21/2023 migrate to Optional
    private Photo descriptionPhoto;

    public static FileCollection create() {
        return FileCollection.builder()
                .bannerPhotos(new ArrayList<>())
                .descriptionPhoto(null)
                .build();
    }

    @JsonIgnore
    public List<Photo> getAll() {
        List<Photo> allPhotos = new ArrayList<>(bannerPhotos);
        if (Objects.nonNull(descriptionPhoto)) allPhotos.add(descriptionPhoto);
        return allPhotos;
    }

    @JsonIgnore
    public Photo getPhotoByHash(UUID hash) {
        if (Objects.nonNull(descriptionPhoto) && descriptionPhoto.getHash().equals(hash)) return descriptionPhoto;
        return bannerPhotos.stream()
                .filter(photo -> Objects.equals(photo.getHash(), hash))
                .findFirst()
                .orElseThrow(() -> new FileNotFoundException("File not found by id: " + hash));
    }

    @JsonIgnore
    public Optional<Photo> getPhotoByOrder(Integer position) {
        return bannerPhotos.stream()
                .filter(photo -> Objects.equals(photo.getOrder(), position))
                .findFirst();
    }

    @JsonIgnore
    public Photo remove(UUID hash) {
        if (Objects.nonNull(descriptionPhoto) && descriptionPhoto.getHash().equals(hash)) {
            Photo temp = descriptionPhoto;
            descriptionPhoto = null;
            return temp;
        }
        Photo photo = bannerPhotos.stream()
                .filter(it -> it.getHash().equals(hash))
                .findFirst()
                .orElseThrow(() -> new FileNotFoundException("File not found by id: " + hash));
        bannerPhotos.remove(photo);
        return photo;
    }
}


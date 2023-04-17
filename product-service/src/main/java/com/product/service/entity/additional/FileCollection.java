package com.product.service.entity.additional;

import com.product.service.exception.exceptions.photo.found.FileNotFoundException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Data
@Builder
@Jacksonized
public class FileCollection {
    private List<Photo> bannerPhotos;
    private Photo descriptionPhoto;

    public List<Photo> getAll() {
        List<Photo> allPhotos = new ArrayList<>(bannerPhotos);
        allPhotos.add(descriptionPhoto);
        return allPhotos;
    }

    public Photo getPhotoByHash(UUID hash) {
        if (descriptionPhoto.getHash().equals(hash)) return descriptionPhoto;
        return bannerPhotos.stream()
                .filter(photo -> Objects.equals(photo.getHash(), hash))
                .findFirst()
                .orElseThrow(() -> new FileNotFoundException("File not found by id: " + hash));
    }

    public Optional<Photo> getPhotoByOrder(Integer position) {
        return bannerPhotos.stream()
                .filter(photo -> Objects.equals(photo.getOrder(), position))
                .findFirst();
    }

    public Photo remove(UUID hash) {
        if (descriptionPhoto.getHash().equals(hash)) {
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

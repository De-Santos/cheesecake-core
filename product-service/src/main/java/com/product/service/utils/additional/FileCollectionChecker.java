package com.product.service.utils.additional;

import com.product.service.dto.photo.additional.FileCollectionDto;
import com.product.service.dto.photo.additional.PhotoDto;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.FileCollection;
import com.product.service.exception.exceptions.file.collection.update.FileCollectionNotIdentityUpdateException;
import com.product.service.exception.exceptions.file.photo.bounds.FileOutOfBoundsException;
import com.product.service.exception.exceptions.file.photo.illegal.IllegalFilePositionUpdateException;
import com.product.service.exception.exceptions.product.exceeded.FileLimitExceededException;
import com.product.service.utils.protector.Protector;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FileCollectionChecker {
    // TODO: 4/16/2023 make configuration from database
    private static final Integer MAX_FILE_COUNT = 9;

    public void checkFileOrder(FileCollection fileCollection) {
        if (fileCollection.getBannerPhotos().size() >= MAX_FILE_COUNT) {
            throw new FileLimitExceededException("Can't be more files than: " + MAX_FILE_COUNT);
        }
    }

    public void checkFileCollectionBounds(Integer position) {
        if (position > MAX_FILE_COUNT) {
            throw new FileOutOfBoundsException("Insert position can't be more than : " + MAX_FILE_COUNT);
        }
    }

    public void checkFileCollectionIdentity(FileCollection fileCollection, FileCollectionDto fileCollectionDto) {
        Protector.notNullRequired(fileCollection, fileCollectionDto);
        this.checkDescriptionPhotoIdentity(fileCollection, fileCollectionDto);
        this.checkBannerPhotosIdentity(fileCollection, fileCollectionDto);
    }

    private void checkBannerPhotosIdentity(FileCollection fileCollection, FileCollectionDto fileCollectionDto) {
        if (!Objects.equals(fileCollection.getBannerPhotos().size(), fileCollectionDto.getBannerPhotos().size())) {
            throw new FileCollectionNotIdentityUpdateException();
        }

        Map<Long, Integer> map = fileCollection.getBannerPhotos().stream()
                .collect(Collectors.groupingBy(BannerPhoto::getId, Collectors.summingInt(e -> 1)));
        Set<Integer> positionSet = new HashSet<>();
        for (PhotoDto photoDto : fileCollectionDto.getBannerPhotos()) {
            int pos = photoDto.getPosition();
            if (pos <= 0 || pos > MAX_FILE_COUNT || positionSet.contains(pos))
                throw IllegalFilePositionUpdateException.create(pos);
            positionSet.add(pos);
            Long id = photoDto.getId();
            if (map.containsKey(id)) {
                int count = map.get(id);
                if (count == 1) {
                    map.remove(id);
                } else {
                    map.put(id, count - 1);
                }
            } else {
                throw new FileCollectionNotIdentityUpdateException();
            }
        }
    }

    private void checkDescriptionPhotoIdentity(FileCollection fileCollection, FileCollectionDto fileCollectionDto) {
        if (Objects.isNull(fileCollection.getDescriptionPhoto()) != Objects.isNull(fileCollectionDto.getDescriptionPhoto()))
            throw new FileCollectionNotIdentityUpdateException("Description photo id must be identity");
        if (Objects.isNull(fileCollection.getDescriptionPhoto())) return;
        if (!Objects.equals(fileCollection.getDescriptionPhoto().getId(), fileCollectionDto.getDescriptionPhoto().getId()))
            throw new FileCollectionNotIdentityUpdateException("Description photo id must be identity");
    }

}

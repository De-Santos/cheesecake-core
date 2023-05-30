package com.product.service.utils.request.utils;

import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.FileCollection;
import com.product.service.utils.protector.Protector;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileCollectionUtils {

    // FIXME: 5/10/2023
    public static FileCollection positionNormalization(FileCollection fileCollection) {
        List<BannerPhoto> bannerList = Protector.safeNull(fileCollection.getBannerPhotos());
        if (bannerList.isEmpty()) return fileCollection;
        bannerList.sort(Comparator.comparing(BannerPhoto::getPosition));
        for (int i = 0; i < bannerList.size(); i++) {
            bannerList.get(i).setPosition(i + 1);
        }
        return fileCollection;
    }

}

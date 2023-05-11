package com.product.service.utils.request.utils;

import additional.ObjectGenerator;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.FileCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileCollectionUtilsTest {

    @Test
    void positionNormalization() {
        FileCollection fileCollection = ObjectGenerator.fileCollection.generate(10);
        fileCollection.getBannerPhotos().remove(3);
        System.out.println();
        FileCollectionUtils.positionNormalization(fileCollection);
        Integer[] expectedPositions = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] actualPositions = fileCollection.getBannerPhotos().stream()
                .map(BannerPhoto::getPosition)
                .toArray(Integer[]::new);
        assertArrayEquals(expectedPositions, actualPositions);
    }

    @Test
    void positionNormalization_EmptyCollection() {
        FileCollection fileCollection = ObjectGenerator.fileCollection.generate(0);
        FileCollection result = FileCollectionUtils.positionNormalization(fileCollection);
        assertTrue(result.getBannerPhotos().isEmpty());
    }

    @Test
    void positionNormalization_SingleElementCollection() {
        FileCollection fileCollection = ObjectGenerator.fileCollection.generate(1);
        FileCollection result = FileCollectionUtils.positionNormalization(fileCollection);
        assertEquals(1, result.getBannerPhotos().get(0).getPosition());
    }

    @Test
    void positionNormalization_NoChangeInPositions() {
        FileCollection fileCollection = ObjectGenerator.fileCollection.generate(5);
        FileCollection result = FileCollectionUtils.positionNormalization(fileCollection);
        assertArrayEquals(fileCollection.getBannerPhotos().toArray(), result.getBannerPhotos().toArray());
    }

    @Test
    void positionNormalization_RandomElementsRemoved() {
        FileCollection fileCollection = ObjectGenerator.fileCollection.generate(9);
        fileCollection.getBannerPhotos().remove(1);
        fileCollection.getBannerPhotos().remove(5);
        fileCollection.getBannerPhotos().remove(6);
        FileCollection result = FileCollectionUtils.positionNormalization(fileCollection);
        Integer[] expectedPositions = new Integer[]{1, 2, 3, 4, 5, 6};
        Integer[] actualPositions = result.getBannerPhotos().stream()
                .map(BannerPhoto::getPosition)
                .toArray(Integer[]::new);
        assertArrayEquals(expectedPositions, actualPositions);
    }
}

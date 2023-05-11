package additional;

import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.FileCollection;

import java.util.ArrayList;
import java.util.List;

public class ObjectGenerator {
    private static List<BannerPhoto> bannerPhotoGenerator(int count) {
        List<BannerPhoto> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(BannerPhoto.builder()
                    .id((long) (i + 1))
                    .position(i + 1)
                    .build()
            );
        }
        return list;
    }

    public static class fileCollection {

        public static FileCollection generate(Integer elementCount) {
            return FileCollection.builder()
                    .id(1L)
                    .bannerPhotos(ObjectGenerator.bannerPhotoGenerator(elementCount))
                    .build();
        }

    }
}

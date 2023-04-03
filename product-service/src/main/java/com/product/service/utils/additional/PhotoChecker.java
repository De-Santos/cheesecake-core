package com.product.service.utils.additional;

import com.product.service.dto.product.ProductRequest;
import com.product.service.utils.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.PhotoNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotoChecker {

    private final Template template;

    public void check(ProductRequest productRequest) {
        List<String> photos = new ArrayList<>(productRequest.getImagesId());
        photos.add(productRequest.getDescriptionImageId());
        List<String> result = photos.stream()
                .filter(id -> !template.isRealFile(id)).toList();
        if (result.isEmpty()) return;
        throw new PhotoNotFoundException(result.toString());
    }
}

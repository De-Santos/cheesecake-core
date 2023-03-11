package com.product.service.productservice.utils.additional;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.product.service.productservice.dto.product.ProductRequest;

import com.product.service.productservice.utils.template.Template;

import lombok.RequiredArgsConstructor;
import ua.cheesecake.dto.exception.PhotoNotFoundException;

@Component
@RequiredArgsConstructor
public class PhotoChecker {

    private final Template template;

    public boolean check(ProductRequest productRequest) {
        List<String> photos = productRequest.getImagesId();
        photos.add(productRequest.getDescriptionImageId());
        List<String> result = photos.stream().filter(id -> !template.isRealFile(id)).collect(Collectors.toList());
        if (result.isEmpty())
            return true;
        else throw new PhotoNotFoundException(result.toString());
    }
}

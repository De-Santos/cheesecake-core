package com.product.service.utils.build;

import com.product.service.dto.tag.TagRequest;
import com.product.service.dto.tag.TagResponse;
import org.springframework.stereotype.Component;

@Component
public class EntityBuilder {

    public TagResponse build(TagRequest tagRequest, Long tagId) {
        return TagResponse.builder()
                .id(tagId)
                .tagName(tagRequest.getTagName())
                .build();
    }
}

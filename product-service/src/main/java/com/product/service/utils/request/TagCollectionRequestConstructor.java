package com.product.service.utils.request;

import com.product.service.dao.TagCollectionRepository;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.additional.tag.TagCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class TagCollectionRequestConstructor {
    private final TagCollectionRepository tagCollectionRepository;

    public TagCollection create(DraftProduct draftProduct) {
        log.info("Creating TagCollection for DraftProduct by draftProduct id: {}", draftProduct.getId());
        return tagCollectionRepository.save(TagCollection.create(draftProduct));
    }
}

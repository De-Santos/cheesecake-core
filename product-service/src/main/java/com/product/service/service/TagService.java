package com.product.service.service;

import com.product.service.dto.product.DraftProductDto;
import com.product.service.dto.tag.TagRequest;
import com.product.service.dto.tag.TagResponse;
import com.product.service.utils.check.ProductChecker;
import com.product.service.utils.request.TagRequestConstructor;
import com.product.service.utils.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRequestConstructor tagRequestConstructor;
    private final RequestValidator requestValidator;
    private final ProductChecker productChecker;


    public DraftProductDto addNewTagToDraftProduct(Long draftId, TagRequest tagRequest) {
        productChecker.checkDraft(draftId);
        requestValidator.validate(tagRequest);
        return tagRequestConstructor.addNewTagToDraftProduct(draftId, tagRequest);
    }

    public DraftProductDto addTagToDraftProduct(Long draftId, Long tagId) {
        productChecker.checkDraft(draftId);
        return tagRequestConstructor.addTagToDraftProduct(draftId, tagId);
    }

    public List<TagResponse> getTags() {
        return tagRequestConstructor.getAll();
    }

    public TagResponse createTag(TagRequest tagRequest) {
        requestValidator.validate(tagRequest);
        return tagRequestConstructor.createTag(tagRequest);
    }

    public DraftProductDto deleteTagFromDraft(Long draftId, Long tagId) {
        productChecker.checkDraft(draftId);
        return tagRequestConstructor.deleteTagFromDraft(draftId, tagId);
    }

    public TagResponse deleteTag(Long tagId) {
        return tagRequestConstructor.deleteTag(tagId);
    }
}

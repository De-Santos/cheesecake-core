package com.product.service.service;

import com.product.service.dto.tag.TagRequest;
import com.product.service.dto.tag.TagResponse;
import com.product.service.utils.check.ProductChecker;
import com.product.service.utils.check.TagChecker;
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
    private final TagChecker tagChecker;


    public List<TagResponse> addNewTagToDraftProduct(Long draftId, TagRequest tagRequest) {
        productChecker.checkDraft(draftId);
        requestValidator.createValidation(tagRequest);
        return tagRequestConstructor.addNewTagToDraftProduct(draftId, tagRequest);
    }

    public List<TagResponse> addTagToDraftProduct(Long draftId, Long tagId) {
        productChecker.checkDraft(draftId);
        tagChecker.checkExistence(tagId);
        tagChecker.checkTagMatchingToProductExistence(tagId, draftId);
        return tagRequestConstructor.addTagToDraftProduct(draftId, tagId);
    }

    public List<TagResponse> getTags() {
        return tagRequestConstructor.getAll();
    }

    public TagResponse createTag(TagRequest tagRequest) {
        requestValidator.createValidation(tagRequest);
        return tagRequestConstructor.createTag(tagRequest);
    }

    public List<TagResponse> deleteTagFromDraft(Long draftId, Long tagId) {
        productChecker.checkDraft(draftId);
        tagChecker.checkTagMatchingToDraftProduct(tagId, draftId);
        return tagRequestConstructor.deleteTagFromDraft(draftId, tagId);
    }

    public TagResponse deleteTag(Long tagId) {
        return tagRequestConstructor.deleteTag(tagId);
    }

    public List<TagResponse> getTagsByDraftId(Long draftId) {
        productChecker.checkDraft(draftId);
        return tagRequestConstructor.getTagsByDraftId(draftId);
    }
}

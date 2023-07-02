package com.product.service.utils.request;

import com.product.service.dto.tag.TagRequest;
import com.product.service.dto.tag.TagResponse;
import com.product.service.exception.exceptions.tag.found.TagNotFoundException;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TagRequestConstructor {
    private final JdbcAccelerator accelerator;
//    private final DraftRequestConstructor draftRequestConstructor;

    public TagResponse createTag(TagRequest tagRequest) {
        return accelerator.createTag(tagRequest);
    }

    public TagResponse deleteTag(Long tagId) {
        return accelerator.deleteTag(tagId)
                .orElseThrow(() -> TagNotFoundException.create(tagId));
    }

    public List<TagResponse> getAll() {
        return accelerator.getAllTags();
    }

    public List<TagResponse> addNewTagToDraftProduct(Long draftId, TagRequest tagRequest) {
        return accelerator.addTagToDraft(draftId, this.createTag(tagRequest).getId());
    }

    public List<TagResponse> addTagToDraftProduct(Long draftId, Long tagId) {
        return accelerator.addTagToDraft(draftId, tagId);
    }

    public List<TagResponse> deleteTagFromDraft(Long draftId, Long tagId) {
        return accelerator.deleteTagMatchingToDraft(tagId, draftId);
    }

    public List<TagResponse> getTagsByDraftId(Long draftId) {
        return accelerator.getTagsByDraftId(draftId);
    }
}

package com.product.service.utils.validator;

import com.product.service.dto.tag.TagRequest;
import com.product.service.exception.exceptions.tag.exist.TagNameAlreadyExistException;
import com.product.service.exception.exceptions.tag.invalid.InvalidTagNameException;
import com.product.service.exception.exceptions.tag.nullable.TagNameIsNullException;
import com.product.service.exception.exceptions.tag.nullable.TagRequestIsNullException;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestValidator {
    private final JdbcAccelerator accelerator;

    public void validate(TagRequest tagRequest) {
        if (tagRequest == null) throw TagRequestIsNullException.create();
        if (tagRequest.getTagName() == null) throw TagNameIsNullException.create();
        if (tagRequest.getTagName().isBlank()) throw InvalidTagNameException.create();
    }

    public void createValidation(TagRequest tagRequest) {
        if (tagRequest == null) throw TagRequestIsNullException.create();
        if (tagRequest.getTagName() == null) throw TagNameIsNullException.create();
        if (tagRequest.getTagName().isBlank()) throw InvalidTagNameException.create();
        this.validateTagNameDuplication(tagRequest.getTagName());
    }

    public void validateTagNameDuplication(String tagName) {
        if (accelerator.isTagNameExist(tagName)) throw TagNameAlreadyExistException.create(tagName);
    }
}

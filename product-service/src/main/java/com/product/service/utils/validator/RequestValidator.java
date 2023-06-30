package com.product.service.utils.validator;

import com.product.service.dto.tag.TagRequest;
import com.product.service.exception.exceptions.tag.invalid.InvalidTagNameException;
import com.product.service.exception.exceptions.tag.nullable.TagNameIsNullException;
import com.product.service.exception.exceptions.tag.nullable.TagRequestIsNullException;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public void validate(TagRequest tagRequest) {
        if (tagRequest == null) throw TagRequestIsNullException.create();
        if (tagRequest.getTagName() == null) throw TagNameIsNullException.create();
        if (tagRequest.getTagName().isBlank()) throw InvalidTagNameException.create();
    }
}

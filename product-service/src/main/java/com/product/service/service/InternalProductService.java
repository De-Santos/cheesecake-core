package com.product.service.service;

import com.product.service.utils.additional.ProductChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class InternalProductService {

    private final ProductChecker productChecker;

    public Boolean checkProduct(UUID versionId) {
        return productChecker.checkProductExistence(versionId);
    }

    public void checkProductSequence(List<Long> versionIdList) {
        throw new UnsupportedOperationException("Method is not fixed");
//        productChecker.checkProductSequence(versionIdList);
    }

}

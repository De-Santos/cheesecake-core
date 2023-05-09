package com.product.service.service;

import com.product.service.utils.additional.ProductChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class InternalProductService {

    private final ProductChecker productChecker;

    public Boolean checkProduct(Long versionId) {
        return productChecker.check(versionId);
    }

    public void checkProductSequence(List<String> versionIdList) {
        productChecker.checkProductList(versionIdList);
    }

}

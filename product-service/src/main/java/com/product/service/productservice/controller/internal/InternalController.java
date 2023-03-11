package com.product.service.productservice.controller.internal;

import com.product.service.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/product")
public class InternalController implements InternalApi {
    private final ProductService productService;

    @Override
    @GetMapping("/is/{versionId}")
    public ResponseEntity<Boolean> isRealProduct(@PathVariable String versionId) {
        log.info("Check product by id: {}", versionId);
        Boolean boolResult = productService.checkProduct(versionId);
        log.debug("Check result is: {}", boolResult);
        return ResponseEntity.ok(boolResult);
    }
}

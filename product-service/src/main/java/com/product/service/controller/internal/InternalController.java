package com.product.service.controller.internal;

import com.product.service.service.InternalProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/product")
public class InternalController implements InternalApi {
    private final InternalProductService internalProductService;

    @Override
    @GetMapping("/is/{versionId}")
    public ResponseEntity<Boolean> isRealProduct(@PathVariable UUID versionId) {
        log.debug("Check product by id: {}", versionId);
        return ResponseEntity.ok(internalProductService.checkProduct(versionId));
    }

    @Override
    @PostMapping("/sequence")
    public void isRealProductSequence(@RequestParam List<Long> versionIdList) {
        log.debug("Check product sequence: {}", versionIdList);
        internalProductService.checkProductSequence(versionIdList);
    }

}

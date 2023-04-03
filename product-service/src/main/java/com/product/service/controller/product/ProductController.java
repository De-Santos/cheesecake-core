package com.product.service.controller.product;

import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductRequest;
import com.product.service.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cheesecake.dto.product.ProductResponse;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController implements ProductApi {
    private final ProductService productService;

    @Override
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        log.info("Add product");
        log.debug("ProductRequest is: {}", productRequest);
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @Override
    @PatchMapping("/update/{versionId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String versionId,
            @RequestBody ProductRequest productRequest) {
        log.info("Update product by id: {}", versionId);
        log.debug("Updated product is: {}", productRequest);
        return ResponseEntity.ok(productService.updateProduct(versionId, productRequest));
    }

    @Override
    @PatchMapping("edit/{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable String id) {
        log.info("Edit(active/inactive) product by id: {}", id);
        return ResponseEntity.ok(productService.editProduct(id));
    }

    // ONLY FOR DEVELOP
    @Override
    @DeleteMapping()
    public void deleteAll() {
        log.info("Delete all items in database");
        productService.deleteAll();
    }

    @Override
    @GetMapping("/getArchive")
    public ResponseEntity<List<ProductResponse>> getArchive() {
        log.info("Get all archive products");
        return ResponseEntity.ok(productService.getArchiveProducts());
    }

    @Override
    @GetMapping("/{versionId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String versionId) {
        log.info("Get product by version id: {}", versionId);
        return ResponseEntity.ok(productService.getById(versionId));
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAll() {
        log.info("Get all products");
        return ResponseEntity.ok(productService.getProducts());
    }

    @Override
    @PatchMapping("/mode")
    public ResponseEntity<ProductResponse> sailMode(@RequestBody ModifyingProductRequest modifyingProductRequest) {
        log.info("Set sail price: {} for product: {}", modifyingProductRequest.getSailPrice(),
                modifyingProductRequest.getVersionId());
        return ResponseEntity.ok(productService.sailMode(modifyingProductRequest));
    }

}

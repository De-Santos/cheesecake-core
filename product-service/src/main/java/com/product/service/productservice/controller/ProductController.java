package com.product.service.productservice.controller;

import com.product.service.productservice.dto.product.ProductRequest;
import ua.cheesecake.dto.ProductResponse;
import com.product.service.productservice.sevice.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController implements ProductApi {
    private final ProductService productService;

    @Override
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addPost(@RequestBody ProductRequest productRequest) {
        log.info("add post in mongo database");
        log.debug(productRequest);
        ProductResponse productResponse = productService.addPost(productRequest);
        log.debug("added post: {}", productResponse);
        return ResponseEntity.ok(productResponse);
    }

    @Override
    @GetMapping("/{versionId}")
    public ResponseEntity<ProductResponse> getPost(@PathVariable String versionId) {
        log.info("get product by version id: {}", versionId);
        return ResponseEntity.ok(productService.getPostById(versionId));
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAll() {
        log.info("get all products");
        return ResponseEntity.ok(productService.getAll());
    }

    @Override
    @PatchMapping("/update/{versionId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String versionId,
                                                         @RequestBody ProductRequest productRequest) {
        log.info("update product by id: {}", versionId);
        return ResponseEntity.ok(productService.update(versionId, productRequest));
    }

    @Override
    @PatchMapping("edit/{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable String id) {
        log.info("edit(active/inactive) product by id: {}", id);
        return ResponseEntity.ok(productService.edit(id));
    }

    @Override
    @DeleteMapping()
    public void deleteAll() {
        log.info("delete all items in database");
        productService.deleteAll();
        log.info("successful");
    }

    @Override
    @GetMapping("/getArchive")
    public List<ProductResponse> getArchive() {
        return productService.getArchive();
    }
}

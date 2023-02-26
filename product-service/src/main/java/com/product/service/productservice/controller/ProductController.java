package com.product.service.productservice.controller;

import com.product.service.productservice.dto.ProductRequest;
import com.product.service.productservice.dto.ProductResponse;
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
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addPost(@RequestBody ProductRequest productRequest) {
        log.info("add post in mongo database");
        log.debug(productRequest);
        ProductResponse productResponse = productService.addPost(productRequest);
        log.debug("added post: {}", productResponse);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getPost(@PathVariable String id) {
        log.info("get product by id: {}", id);
        return ResponseEntity.ok(productService.getPostById(id));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAll() {
        log.info("get all products");
        return ResponseEntity.ok(productService.getAll());
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id,
                                                         @RequestBody ProductRequest productRequest) {
        log.info("update product by id: {}", id);
        return ResponseEntity.ok(productService.update(id, productRequest));
    }

    @PatchMapping("edit/{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable String id) {
        log.info("edit(active/inactive) product by id: {}", id);
        return ResponseEntity.ok(productService.edit(id));
    }
}

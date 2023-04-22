package com.product.service.controller.product;

import com.product.service.dto.photo.DraftProductDto;
import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductResponse;
import com.product.service.service.DraftProductService;
import com.product.service.service.ProductService;
import jakarta.validation.constraints.NotNull;
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
    private final DraftProductService draftProductService;

    @Override
    @PostMapping("/add/{draftId}")
    public ResponseEntity<ProductResponse> addProduct(@PathVariable("draftId") @NotNull String draftId) {
        log.info("Add product by draft product: {}", draftId);
        return ResponseEntity.ok(productService.addProduct(draftId));
    }

    @Override
    @PatchMapping("/update/{draftId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("draftId") String id) {
        log.info("Update product by id: {}", id);
        return ResponseEntity.ok(productService.updateProduct(id));
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

    // TODO: 4/19/2023 test me
    @Override
    @GetMapping("/draft/of/{id}")
    public ResponseEntity<DraftProductDto> toDraftProduct(@PathVariable("id") String versionId) {
        log.info("Get draft from product by versionId: {}", versionId);
        return ResponseEntity.ok(productService.toDraft(versionId));
    }

    // TODO: 4/22/2023 check logic
    @Override
    @PostMapping("/draft")
    public ResponseEntity<String> addDraft() {
        log.info("New draft");
        return ResponseEntity.ok(draftProductService.newDraft());
    }

    // TODO: 4/22/2023 check logic
    @Override
    @GetMapping("/draft/{draftId}")
    public ResponseEntity<DraftProductDto> getDraft(@PathVariable("draftId") String id) {
        log.info("Get draft product by id: {}", id);
        return ResponseEntity.ok(draftProductService.get(id));
    }

    // TODO: 4/22/2023 check logic
    @Override
    @GetMapping("/draft")
    public ResponseEntity<List<String>> getDrafts() {
        log.info("Get all drafts");
        return ResponseEntity.ok(draftProductService.get());
    }

    // TODO: 4/22/2023 check logic
    @Override
    @PostMapping("/draft/update")
    public ResponseEntity<DraftProductDto> updateDraftProduct(@RequestBody DraftProductDto draftProductDto) {
        log.info("Update draft product");
        return ResponseEntity.ok(draftProductService.update(draftProductDto));
    }

    // TODO: 4/22/2023 check logic
    @Override
    @DeleteMapping("/draft/{draftId}")
    public ResponseEntity<DraftProductDto> deleteDraftProduct(@PathVariable("draftId") String id) {
        log.info("Delete draft product by id: {}", id);
        return ResponseEntity.ok(draftProductService.delete(id));
    }
}

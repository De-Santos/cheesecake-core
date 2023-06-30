package com.product.service.controller.product;

import com.product.service.dto.product.DraftProductDto;
import com.product.service.dto.product.ProductResponse;
import com.product.service.dto.product.SaleProductRequest;
import com.product.service.dto.tag.TagRequest;
import com.product.service.dto.tag.TagResponse;
import com.product.service.service.DraftProductService;
import com.product.service.service.ProductService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController implements ProductApi {
    private final ProductService productService;
    private final DraftProductService draftProductService;

    @Override
    @PostMapping("/add/{draftId}")
    public ResponseEntity<UUID> addProduct(@PathVariable("draftId") @NotNull Long draftId) {
        log.info("Add product by draft product: {}", draftId);
        return ResponseEntity.ok(productService.addProduct(draftId));
    }

    @Override
    @PatchMapping("/update/{draftId}")
    public ResponseEntity<UUID> updateProduct(@PathVariable("draftId") Long id) {
        log.info("Update product by id: {}", id);
        return ResponseEntity.ok(productService.updateProduct(id));
    }

    @Override
    @PatchMapping("edit/{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable("id") UUID id) {
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
    @GetMapping("/archive")
    public ResponseEntity<List<ProductResponse>> getArchive() {
        log.info("Get all archive products");
        return ResponseEntity.ok(productService.getArchiveProducts());
    }

    @Override
    @GetMapping("/{versionId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("versionId") UUID versionId) {
        log.info("Get product by version id: {}", versionId);
        return ResponseEntity.ok(productService.getById(versionId));
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<UUID>> getAllProducts() {
        log.info("Get all products");
        return ResponseEntity.ok(productService.getProducts());
    }

    @Override
    @PatchMapping("/mode")
    public ResponseEntity<ProductResponse> saleMode(@RequestBody SaleProductRequest saleProductRequest) {
        log.info("Set sail price: {} for product: {}", saleProductRequest.getSailPrice(),
                saleProductRequest.getVersionId());
        return ResponseEntity.ok(productService.sailMode(saleProductRequest));
    }

    @Override
    @GetMapping("/draft/of/{versionId}")
    public ResponseEntity<Long> toDraftProduct(@PathVariable("versionId") UUID versionId) {
        log.info("Get draft from product by versionId: {}", versionId);
        return ResponseEntity.ok(productService.toDraft(versionId));
    }

    @Override
    @PostMapping("/draft")
    public ResponseEntity<Long> addDraft() {
        log.info("New draft");
        return ResponseEntity.ok(draftProductService.newDraft());
    }

    @Override
    @GetMapping("/draft/{draftId}")
    public ResponseEntity<DraftProductDto> getDraft(@PathVariable("draftId") Long id) {
        log.info("Get draft product by id: {}", id);
        return ResponseEntity.ok(draftProductService.get(id));
    }

    @Override
    @GetMapping("/draft")
    public ResponseEntity<List<Long>> getDrafts() {
        log.info("Get all drafts");
        return ResponseEntity.ok(draftProductService.get());
    }

    @Override
    @PatchMapping("/draft/update")
    public ResponseEntity<DraftProductDto> updateDraftProduct(@RequestBody DraftProductDto draftProductDto) {
        log.info("Update draft product");
        return ResponseEntity.ok(draftProductService.update(draftProductDto));
    }

    @Override
    @DeleteMapping("/draft/{draftId}")
    public ResponseEntity<DraftProductDto> deleteDraftProduct(@PathVariable("draftId") Long id) {
        log.info("Delete draft product by id: {}", id);
        return ResponseEntity.ok(draftProductService.delete(id));
    }

    @Override
    @PostMapping("/draft/{draftId}/new/tag/")
    public ResponseEntity<DraftProductDto> addNewTagToDraftProduct(@PathVariable("draftId") Long id, @RequestBody TagRequest tagRequest) {
        log.info("Add new tag by: {} to draft product by id: {}", tagRequest, id);
        return ResponseEntity.ok(null);
    }

    @Override
    @PatchMapping("/draft/{draftId}/tag")
    public ResponseEntity<DraftProductDto> addTagToDraftProduct(@PathVariable("draftId") Long id, @RequestParam("id") Long tagId) {
        log.info("Add tag by id: {} to draft product by id: {}", tagId, id);
        return ResponseEntity.ok(null);
    }

    @Override
    @GetMapping("/tag")
    public ResponseEntity<List<TagResponse>> getTags() {
        log.info("Get all tags");
        return ResponseEntity.ok(null);
    }

    @Override
    @PostMapping("/tag")
    public ResponseEntity<TagResponse> createTag(@RequestBody TagRequest tagRequest) {
        log.info("Create tag by: {}", tagRequest);
        return ResponseEntity.ok(null);
    }

    @Override
    @DeleteMapping("/draft/{draftId}/tag")
    public ResponseEntity<DraftProductDto> deleteTagFromDraft(@PathVariable("draftId") Long id, @RequestParam("id") Long tagId) {
        log.info("Delete tag by id: {} from draft product by id: {}", tagId, id);
        return ResponseEntity.ok(null);
    }

    @Override
    @DeleteMapping("tag/{tagId}")
    public ResponseEntity<TagResponse> deleteTag(@PathVariable("tagId") Long tagId) {
        log.info("Delete tag by id: {}", tagId);
        return ResponseEntity.ok(null);
    }
}

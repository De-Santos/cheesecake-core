package com.user.service.controller.wish;


import com.user.service.dto.wish.WishListRequest;
import com.user.service.dto.wish.WishProductResponse;
import com.user.service.service.WishListService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wishList")
@CrossOrigin(origins = "*")
public class WishListController implements WishListApi {

    private final WishListService wishListService;

    @Override
    @PutMapping()
    public ResponseEntity<WishProductResponse> addItem(@NotNull WishListRequest wishListRequest) {
        log.info("Add item to wishList");
        log.debug(wishListRequest);
        return ResponseEntity.ok(wishListService.addWishItem(wishListRequest));
    }

    @Override
    @DeleteMapping()
    public ResponseEntity<Boolean> deleteItem(@NotNull WishListRequest wishListRequest) {
        log.info("Delete item in wish list");
        log.debug(wishListRequest);
        return ResponseEntity.ok(wishListService.deleteWishItem(wishListRequest));
    }

    @Override
    @PostMapping("/check")
    public ResponseEntity<Boolean> checkItem(@NotNull WishListRequest wishListRequest) {
        log.info("Check item in wishList");
        log.debug(wishListRequest);
        return ResponseEntity.ok(wishListService.checkItemInWishList(wishListRequest));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<List<Long>> getWishList(@PathVariable(name = "id") Long userId) {
        log.info("Get wishList");
        log.debug(userId);
        return ResponseEntity.ok(wishListService.getWishList(userId));
    }
}

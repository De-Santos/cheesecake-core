package com.user.service.controller.wish;


import com.user.service.dto.wishList.WishListRequest;
import com.user.service.service.WishListService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wishList")
public class WishListController implements WishListApi {

    private final WishListService wishListService;

    @Override
    @PostMapping("/add")
    public ResponseEntity<Boolean> addItem(@NotNull WishListRequest wishListRequest) {
        log.info("Add item to wishList");
        log.debug(wishListRequest);
        return ResponseEntity.ok(wishListService.addWishItem(wishListRequest));
    }

    @Override
    @DeleteMapping("/delete")
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
    public ResponseEntity<List<String>> getWishList(@NotNull String userId) {
        log.info("Get wishList");
        log.debug(userId);
        return ResponseEntity.ok(wishListService.getWishList(Long.valueOf(userId)));
    }
}

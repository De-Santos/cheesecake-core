package com.order.service.orderservice.controller.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.order.service.orderservice.dao.WishListRepository;
import com.order.service.orderservice.dto.wishList.WishListResponse;
import com.order.service.orderservice.service.GlobalService;
import com.order.service.orderservice.utils.convertor.Convertor;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InternalController implements InternalApi {
    
    private final GlobalService globalService;
    private final WishListRepository wishListRepository;
    private final Convertor convertor;

    @Override
    @PostMapping("/init")
    public ResponseEntity<Boolean> initialization(Long userId) {
        log.info("--- Creating basket and wish list for user: {}", userId);
        globalService.createAll(userId);
        return ResponseEntity.ok(true);
    }

    @Override
    @GetMapping("/getWishList")
    public ResponseEntity<List<WishListResponse>> getWishList() {
        log.info("Get all wishList");
        List<WishListResponse> list = wishListRepository.findAll().stream().map(convertor::convert).toList();
        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/getBasket")
    public ResponseEntity<List<WishListResponse>> getBasket() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBasket'");
    }

    
}
 
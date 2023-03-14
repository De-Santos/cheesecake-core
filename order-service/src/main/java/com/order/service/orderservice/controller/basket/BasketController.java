package com.order.service.orderservice.controller.basket;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.order.service.orderservice.dto.basket.BasketProductDto;
import com.order.service.orderservice.dto.basket.BasketRequest;
import com.order.service.orderservice.dto.basket.BasketResponse;
import com.order.service.orderservice.service.BasketService;

import jakarta.validation.constraints.NotNull;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/basket")
public class BasketController implements BasketApi {
    
    private final BasketService basketService;

    @Override
    @PutMapping("/add")
    public ResponseEntity<Boolean> addItem(@NotNull BasketRequest basketRequest) {
        log.info("Add item in basket");
        log.debug(basketRequest);
        return ResponseEntity.ok(basketService.add(basketRequest));
    }

    @Override
    @DeleteMapping()
    public ResponseEntity<Boolean> deleteItem(@NotNull BasketRequest basketRequest) {
        log.info("Delete item in basket");
        log.debug(basketRequest);
        return ResponseEntity.ok(basketService.delete(basketRequest));
    }

    @Override
    @PatchMapping("/update")
    public ResponseEntity<BasketProductDto> updateBasketProduct(@NotNull BasketRequest basketRequest) {
        log.info("Update basket");
        log.debug(basketRequest);
        return ResponseEntity.ok(basketService.update(basketRequest));
    }

    @Override
    @PostMapping("/check")
    public ResponseEntity<Boolean> checkItem(@NotNull BasketRequest basketRequest) {
        log.info("Check item in basket");
        log.debug(basketRequest);
        return ResponseEntity.ok(basketService.check(basketRequest));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BasketResponse> getBasket(@NotNull Long userId) {
        log.info("Get basket");
        log.debug(userId);
        return ResponseEntity.ok(basketService.get(userId));
    }

}

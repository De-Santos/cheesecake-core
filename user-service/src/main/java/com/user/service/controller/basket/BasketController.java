package com.user.service.controller.basket;


import com.user.service.dto.basket.BasketProductDto;
import com.user.service.dto.basket.BasketRequest;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.service.BasketService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/basket")
public class BasketController implements BasketApi {
    
    private final BasketService basketService;

    @Override
    @PutMapping()
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
    @PatchMapping()
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
    public ResponseEntity<BasketResponse> getBasket(@NotNull @PathVariable(name = "id") Long userId) {
        log.info("Get basket");
        log.debug(userId);
        return ResponseEntity.ok(basketService.get(userId));
    }

}
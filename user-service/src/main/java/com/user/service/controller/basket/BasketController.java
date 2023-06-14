package com.user.service.controller.basket;


import com.user.service.dto.basket.BasketProductDto;
import com.user.service.dto.basket.BasketRequest;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.service.BasketService;
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
    public ResponseEntity<Boolean> addItem(@RequestBody BasketRequest basketRequest) {
        log.info("Add item in basket");
        return ResponseEntity.ok(basketService.add(basketRequest));
    }

    @Override
    @DeleteMapping()
    public ResponseEntity<Boolean> deleteItem(@RequestBody BasketRequest basketRequest) {
        log.info("Delete item in basket");
        return ResponseEntity.ok(basketService.delete(basketRequest));
    }

    @Override
    @PatchMapping()
    public ResponseEntity<BasketProductDto> updateBasketProduct(@RequestBody BasketRequest basketRequest) {
        log.info("Update basket");
        return ResponseEntity.ok(basketService.update(basketRequest));
    }

    @Override
    @PostMapping("/check")
    public ResponseEntity<Boolean> checkItem(@RequestBody BasketRequest basketRequest) {
        log.info("Check item in basket");
        return ResponseEntity.ok(basketService.check(basketRequest));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BasketResponse> getBasket(@PathVariable(name = "id") Long userId) {
        log.info("Get basket");
        return ResponseEntity.ok(basketService.get(userId));
    }

}

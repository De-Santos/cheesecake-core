package com.order.service.orderservice.utils.request;

import com.order.service.orderservice.dao.BasketProductRepository;
import com.order.service.orderservice.dao.BasketRepository;
import com.order.service.orderservice.dto.basket.BasketProductDto;
import com.order.service.orderservice.dto.basket.BasketResponse;
import com.order.service.orderservice.entities.Basket;
import com.order.service.orderservice.entities.BasketProduct;
import com.order.service.orderservice.exceptions.exceptions.BasketNotFoundException;
import com.order.service.orderservice.exceptions.exceptions.BasketProductNotFoundException;
import com.order.service.orderservice.utils.convertor.Convertor;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class BasketRequestConstructor {

    private final BasketRepository basketRepository;
    private final BasketProductRepository basketProductRepository;
    private final Convertor convertor;

    public boolean addItemToBasket(Long userId, String versionId, Integer count) {
        Basket basket = basketRepository.findById(userId).orElseThrow(BasketNotFoundException::new);
        log.debug("addItemToBasket get versionId: {} response basket is: {}", versionId, basket);
        List<String> products = basket.getProductList().stream().map(BasketProduct::getProductId).toList();
        if (!products.contains(versionId)) {
            basketProductRepository.save(convertor.convert(basket, versionId, count));
            return true;
        }
        return false;
    }

    public boolean deleteItemFromBasket(Long userId, String versionId) {
        Optional<BasketProduct> basketProductId = findBasketProductIdByVersionId(versionId, userId);
        log.debug("deleteItemFromBasket get basketProduct: {}", basketProductId);
        if (basketProductId.isPresent()) {
            basketProductRepository.deleteTest(basketProductId.get().getId(), versionId);
            return true;
        }
        return false;
    }
    
    public BasketProductDto updateItemFromBasket(Long userId, String versionId, Integer count) {
        BasketProduct basketProduct = findBasketProductIdByVersionId(versionId, userId)
                .orElseThrow(BasketProductNotFoundException::new);
        log.debug("updateItemFromBasket get basketProduct: {}", basketProduct);
        BasketProduct newBasketProduct = basketProductRepository.save(convertor.setConvert(basketProduct, count));
        log.debug("updated basket product is: {}", newBasketProduct);
        return convertor.getBasketProductDto(newBasketProduct);
    }

    private Optional<BasketProduct> findBasketProductIdByVersionId(String versionId, Long userId) {
        Basket basket = basketRepository.findById(userId).orElseThrow(BasketNotFoundException::new);
        return basket
                .getProductList()
                .stream()
                .filter(it -> it.getProductId().equals(versionId))
                .findAny();
    }

    public boolean checkItemFromBasket(Long userId, String versionId) {
        Basket basket = basketRepository.findById(userId).orElseThrow(BasketNotFoundException::new);
        log.debug("checkItemFromBasket get versionId: {}, basket: {} ", versionId, basket);
        return basketProductRepository.existsBasketProductByBasketAndProductId(basket, versionId);
    }

    public BasketResponse getItemsFromBasket(@NotNull Long userId) {
        Basket basket = basketRepository.findById(userId).orElseThrow(BasketNotFoundException::new);
        return convertor.convert(basket);
    }
}



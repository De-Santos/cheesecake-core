package com.user.service.utils.request;

import com.user.service.dao.BasketProductRepository;
import com.user.service.dao.BasketRepository;
import com.user.service.dto.basket.BasketProductDto;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.entities.Basket;
import com.user.service.entities.BasketProduct;
import com.user.service.exceptions.exceptions.BasketNotFoundException;
import com.user.service.exceptions.exceptions.BasketProductNotFoundException;
import com.user.service.utils.convertor.Convertor;
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
        if (basketProductContainsMapper(basket, versionId)) {
            basketProductRepository.save(convertor.convert(basket, versionId, count));
            return true;
        }
        return false;
    }

    private boolean basketProductContainsMapper(Basket basket, String versionId) {
        List<BasketProduct> productList = basket.getProductList();
        if (productList != null) {
            List<String> products = productList.stream().map(BasketProduct::getProductId).toList();
            return products.contains(versionId);
        }
        return true;
    }

    public boolean checkItemFromBasket(Long userId, String versionId) {
        Basket basket = basketRepository.findById(userId).orElseThrow(BasketNotFoundException::new);
        log.debug("checkItemFromBasket get versionId: {}, basket: {} ", versionId, basket);
        return basketProductRepository.existsBasketProductByBasketAndProductId(basket, versionId);
    }

    public void deleteBasket(Long userId) {
        Basket basket = basketRepository.findById(userId).orElseThrow(BasketNotFoundException::new);
        log.debug("deleteBasket delete basket by id: {}", userId);
        basketProductRepository.forceDeleteProductsByBasketId(basket);
        basketRepository.deleteById(userId);
    }

    public boolean deleteItemFromBasket(Long userId, String versionId) {
        Optional<BasketProduct> basketProductId = findBasketProductIdByVersionId(versionId, userId);
        log.debug("deleteItemFromBasket get basketProduct: {}", basketProductId);
        if (basketProductId.isPresent()) {
            basketProductRepository.forceDelete(basketProductId.get().getId(), versionId);
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

    public BasketResponse getItemsFromBasket(@NotNull Long userId) {
        Basket basket = basketRepository.findById(userId).orElseThrow(BasketNotFoundException::new);
        return convertor.convert(basket);
    }
}

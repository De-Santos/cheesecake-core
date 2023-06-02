package com.user.service.service;


import com.user.service.dto.basket.BasketProductDto;
import com.user.service.dto.basket.BasketRequest;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.utils.additional.checker.SuperBasketChecker;
import com.user.service.utils.request.BasketRequestConstructor;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class BasketService {

    private final SuperBasketChecker superBasketChecker;
    private final BasketRequestConstructor basketRequestConstructor;

    public boolean add(@NotNull BasketRequest basketRequest) {
        Long userId = Long.valueOf(basketRequest.getUserId());
        String versionId = basketRequest.getProductVersionId();
        Integer count = basketRequest.getCount();
        superBasketChecker.checkBasketExistence(userId, versionId, count);
        return basketRequestConstructor.addItemToBasket(userId, versionId, count);
    }

    @Transactional
    public boolean delete(@NotNull BasketRequest basketRequest) {
        Long userId = Long.valueOf(basketRequest.getUserId());
        String versionId = basketRequest.getProductVersionId();
        Integer count = basketRequest.getCount();
        superBasketChecker.checkBasketExistence(userId, versionId, count);
        return basketRequestConstructor.deleteItemFromBasket(userId, versionId);
    }

    public BasketProductDto update(@NotNull BasketRequest basketRequest) {
        Long userId = Long.valueOf(basketRequest.getUserId());
        String versionId = basketRequest.getProductVersionId();
        Integer count = basketRequest.getCount();
        superBasketChecker.checkBasketExistence(userId, versionId, count);
        return basketRequestConstructor.updateItemFromBasket(userId, versionId, count);
    }

    public boolean check(@NotNull BasketRequest basketRequest) {
        Long userId = Long.valueOf(basketRequest.getUserId());
        String versionId = basketRequest.getProductVersionId();
        Integer count = basketRequest.getCount();
        superBasketChecker.checkBasketExistence(userId, versionId, count);
        return basketRequestConstructor.checkItemFromBasket(userId, versionId);
    }

    public BasketResponse get(@NotNull Long userId) {
        superBasketChecker.check(userId);
        return basketRequestConstructor.getItemsFromBasket(userId);
    }
}

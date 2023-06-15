package com.user.service.utils.request;

import com.user.service.dao.BasketProductRepository;
import com.user.service.dao.BasketRepository;
import com.user.service.dto.basket.BasketProductResponse;
import com.user.service.dto.basket.BasketRequest;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.dto.basket.DeleteBasketProductRequest;
import com.user.service.entities.Basket;
import com.user.service.entities.BasketProduct;
import com.user.service.exceptions.exceptions.BasketNotFoundException;
import com.user.service.exceptions.exceptions.BasketProductNotFoundException;
import com.user.service.utils.convertor.Converter;
import com.user.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class BasketRequestConstructor {

    private final BasketRepository basketRepository;
    private final BasketProductRepository basketProductRepository;
    private final JdbcAccelerator accelerator;
    private final Converter converter;

    public BasketProduct addItemToBasket(BasketRequest basketRequest) {
        Basket basket = basketRepository.findById(basketRequest.getUserId()).orElseThrow(BasketNotFoundException::new);
        Optional<Long> id = accelerator.getBasketProductIdBy(basketRequest.getProductVersionId(), basket.getId());
        return id.map(
                it -> basketProductRepository.save(converter.convert(it, basket, basketRequest))
        ).orElseGet(
                () -> basketProductRepository.save(converter.convert(basket, basketRequest))
        );
    }

    public boolean checkItemFromBasket(BasketRequest basketRequest) {
        return accelerator.existBasketProductBy(basketRequest.getProductVersionId(), basketRequest.getUserId());
    }

    public boolean deleteItemFromBasket(DeleteBasketProductRequest deleteRequest) {
        if (Boolean.TRUE.equals(accelerator.existBasketProductBy(deleteRequest.getProductVersionId(), deleteRequest.getUserId())))
            throw BasketProductNotFoundException.create(deleteRequest.getUserId(), deleteRequest.getProductVersionId());
        basketProductRepository.forceDelete(deleteRequest.getUserId(), deleteRequest.getProductVersionId());
        return true;
    }

    public BasketProductResponse updateItemFromBasket(BasketRequest basketRequest) {
        return accelerator.updateCountInBasketProductBy(basketRequest.getProductVersionId(), basketRequest.getUserId())
                .orElseThrow(() -> BasketProductNotFoundException.create(basketRequest.getUserId(), basketRequest.getProductVersionId()));
    }

    public BasketResponse getItemsFromBasket(Long userId) {
        Basket basket = basketRepository.findById(userId).orElseThrow(BasketNotFoundException::new);
        return converter.convert(basket);
    }
}

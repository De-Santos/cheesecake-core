package com.user.service.service;


import com.user.service.dto.basket.BasketProductResponse;
import com.user.service.dto.basket.BasketRequest;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.dto.basket.DeleteBasketProductRequest;
import com.user.service.exceptions.exceptions.basket.found.BasketNotFoundException;
import com.user.service.exceptions.exceptions.basket.found.BasketProductNotFoundException;
import com.user.service.utils.builder.ResponseBuilder;
import com.user.service.utils.request.BasketRequestConstructor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRequestConstructor basketRequestConstructor;
    private final ResponseBuilder responseBuilder;

    /**
     * Adds a product to the basket based on the provided {@link BasketRequest}.
     *
     * @param basketRequest The {@link BasketRequest}.
     * @return The {@link BasketProductResponse} for the added product.
     * @throws BasketNotFoundException If the basket is not found for the given userId in the {@link BasketRequest}.
     */
    public BasketProductResponse add(BasketRequest basketRequest) throws BasketNotFoundException {
        return responseBuilder.convert(basketRequestConstructor.addItemToBasket(basketRequest));
    }

    @Transactional
    public boolean delete(DeleteBasketProductRequest deleteBasketProductRequest) {
        return basketRequestConstructor.deleteItemFromBasket(deleteBasketProductRequest);
    }

    /**
     * Updates a product count in the basket by the provided {@link BasketRequest}.
     *
     * @param basketRequest The {@link BasketRequest}.
     * @return The {@link BasketProductResponse} from the updated product.
     * @throws BasketProductNotFoundException If the basket is not found for the given userId in the {@link BasketRequest}.
     */
    public BasketProductResponse update(BasketRequest basketRequest) {
        return basketRequestConstructor.updateItemFromBasket(basketRequest);
    }

    public boolean check(BasketRequest basketRequest) {
        return basketRequestConstructor.checkItemFromBasket(basketRequest);
    }

    public BasketResponse get(Long userId) {
        return basketRequestConstructor.getItemsFromBasket(userId);
    }
}

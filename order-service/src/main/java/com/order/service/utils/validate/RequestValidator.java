package com.order.service.utils.validate;

import com.order.service.dto.*;
import com.order.service.exception.exceptions.basket.empty.BasketProductListIsEmptyException;
import com.order.service.exception.exceptions.basket.exceeded.BasketProductCountExceededException;
import com.order.service.exception.exceptions.basket.found.BasketNotFoundException;
import com.order.service.exception.exceptions.basket.nullable.BasketIdIsNullException;
import com.order.service.exception.exceptions.order.found.OrderNotFoundException;
import com.order.service.exception.exceptions.order.invalid.OrderTotalPriceInvalidException;
import com.order.service.exception.exceptions.order.nullable.*;
import com.order.service.exception.exceptions.order.time.invalid.OrderRequiredDoneTimeInvalidException;
import com.order.service.exception.exceptions.order.time.nullable.OrderRequiredDoneTimeIsNullException;
import com.order.service.exception.exceptions.product.empty.OrderProductListIsEmptyException;
import com.order.service.exception.exceptions.product.exceeded.OrderProductCountExceededException;
import com.order.service.exception.exceptions.product.exist.ProductDoesNotExistInActiveProductsException;
import com.order.service.exception.exceptions.product.nullable.OrderProductCountIsNullException;
import com.order.service.exception.exceptions.product.nullable.OrderProductListIsNullException;
import com.order.service.exception.exceptions.product.nullable.OrderProductVersionIsNullException;
import com.order.service.exception.exceptions.user.found.UserNotFoundException;
import com.order.service.utils.request.accelerator.ValidationAccelerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
public class RequestValidator implements DtoValidator {

    private static final Integer MAX_PRODUCT_COUNT = 100;
    private static final Integer MIN_REQUIRED_DONE_TIME_DAYS = 3;

    private final ValidationAccelerator validationAccelerator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateOrderRequest(OrderRequest orderRequest) {
        if (orderRequest == null) throw OrderRequestIsNullException.create();
        this.validateUserId(orderRequest.getUserId());
        this.validateProducts(orderRequest.getProducts());
        this.validateRequiredDoneTime(orderRequest.getRequiredDoneTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateBasketById(Long basketId) {
        if (basketId == null) throw BasketIdIsNullException.create();
        if (Boolean.FALSE.equals(validationAccelerator.existBasketById(basketId))) throw BasketNotFoundException.create(basketId);
        this.validateBasketProductsByBasketId(basketId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateOrderRejectRequest(Long orderId, RejectOrderRequest rejectOrderRequest) {
        if (orderId == null) throw OrderRequestIsNullException.create();
        this.validateRejectOrderRequest(rejectOrderRequest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateUpdateOrderRequest(UpdateOrderRequest updateOrderRequest) {
        if (updateOrderRequest == null) throw UpdateOrderRequestIsNullException.create();
        if (Boolean.FALSE.equals(validationAccelerator.existOrderById(updateOrderRequest.getId())))
            throw OrderNotFoundException.create(updateOrderRequest.getId());
        this.validateTotalPrice(updateOrderRequest.getTotalPrice());
        this.validateRequiredDoneTime(updateOrderRequest.getRequiredDoneTime());
        this.validateProducts(updateOrderRequest.getOrderProductList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateUpdateOrderStatusRequest(UpdateProcessStatusRequest processStatus) {
        if (processStatus == null) throw UpdateProcessStatusIsNullRequestException.create();
    }

    private void validateUserId(Long userId) {
        if (userId == null) throw UserNameIsNullException.create();
        if (Boolean.TRUE.equals(validationAccelerator.existUserById(userId))) return;
        throw UserNotFoundException.create(userId);
    }

    private void validateProducts(List<OrderProductRequest> products) {
        if (products == null) throw OrderProductListIsNullException.create();
        if (products.isEmpty()) throw OrderProductListIsEmptyException.create();
        products.forEach(this::validateProduct);
    }

    private void validateProduct(OrderProductRequest orderProductRequest) {
        if (orderProductRequest.getCount() == null) throw OrderProductCountIsNullException.create();
        if (orderProductRequest.getProductVersionId() == null) throw OrderProductVersionIsNullException.create();
        if (orderProductRequest.getCount() > MAX_PRODUCT_COUNT)
            throw OrderProductCountExceededException.create(orderProductRequest, MAX_PRODUCT_COUNT);
        if (Boolean.TRUE.equals(validationAccelerator.existProductByVersionId(orderProductRequest.getProductVersionId()))) return;
        throw ProductDoesNotExistInActiveProductsException.create(orderProductRequest.getProductVersionId());
    }

    private void validateRequiredDoneTime(Date requiredDoneTime) {
        if (requiredDoneTime == null) {
            throw OrderRequiredDoneTimeIsNullException.create();
        }

        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, MIN_REQUIRED_DONE_TIME_DAYS);

        if (requiredDoneTime.before(currentDate))
            throw new OrderRequiredDoneTimeInvalidException("Required done time must be after current date.");


        if (requiredDoneTime.before(calendar.getTime()))
            throw new OrderRequiredDoneTimeInvalidException("Required done time must be after " + MIN_REQUIRED_DONE_TIME_DAYS + " days.");
    }

    private void validateBasketProductsByBasketId(Long basketId) {
        Map<UUID, Integer> products = validationAccelerator.getBasketProductsByBasketId(basketId);
        if (products.isEmpty()) throw BasketProductListIsEmptyException.create();
        products.forEach((productVersionId, count) -> {
            if (count > MAX_PRODUCT_COUNT)
                throw BasketProductCountExceededException.create(productVersionId, basketId, MAX_PRODUCT_COUNT);
            if (Boolean.FALSE.equals(validationAccelerator.existProductByVersionId(productVersionId)))
                throw ProductDoesNotExistInActiveProductsException.create(productVersionId);
        });
    }

    private void validateRejectOrderRequest(RejectOrderRequest rejectOrderRequest) {
        if (rejectOrderRequest == null) throw RejectOrderExceptionIsNullRequestException.create();
        if (rejectOrderRequest.getAdminId() == null) throw AdminIdIsNullRequestException.create();
        if (rejectOrderRequest.getMessage() == null) throw RejectOrderMessageIsNullRequestException.create();
    }

    private void validateTotalPrice(BigDecimal totalPrice) {
        if (totalPrice == null) throw OrderTotalPriceIsNullException.create();
        if (totalPrice.compareTo(BigDecimal.ZERO) < 0) throw OrderTotalPriceInvalidException.create(totalPrice);
    }
}

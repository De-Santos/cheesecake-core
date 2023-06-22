package com.order.service.utils.validate;

import com.order.service.dto.OrderRequest;
import com.order.service.dto.RejectOrderRequest;
import com.order.service.dto.UpdateOrderRequest;
import com.order.service.dto.UpdateProcessStatusRequest;
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
import org.springframework.stereotype.Component;

/**
 * The DtoValidator interface defines methods for validating various request DTOs.
 */
@Component
public interface DtoValidator {

    /**
     * Validates the OrderRequest.
     *
     * @param orderRequest The OrderRequest to validate.
     * @throws OrderRequestIsNullException                  If the orderRequest parameter is null.
     * @throws UserNameIsNullException                      If the userId in the orderRequest is null.
     * @throws UserNotFoundException                        If the user with the specified userId does not exist.
     * @throws OrderProductListIsNullException              If the products in the orderRequest is null.
     * @throws OrderProductListIsEmptyException             If the products in the orderRequest is empty.
     * @throws OrderProductCountIsNullException             If the count of any order product in the list is null.
     * @throws OrderProductVersionIsNullException           If the product version id of any order product in the list is null.
     * @throws OrderProductCountExceededException           If the count of any order product exceeds the maximum allowed count.
     * @throws ProductDoesNotExistInActiveProductsException If the product version id of any order product does not exist in the active products.
     * @throws OrderRequiredDoneTimeIsNullException         If the requiredDoneTime in the orderRequest is null.
     * @throws OrderRequiredDoneTimeInvalidException        If the requiredDoneTime is before the current date or less than the minimum required done time.
     */
    void validateOrderRequest(OrderRequest orderRequest);

    /**
     * Validates the basket by its ID.
     *
     * @param basketId The ID of the basket to validate.
     * @throws BasketIdIsNullException                      If the basketId parameter is null.
     * @throws BasketNotFoundException                      If the basket with the specified basketId does not exist.
     * @throws BasketProductListIsEmptyException            If the product list in the basket is empty.
     * @throws BasketProductCountExceededException          If the count of any product in the basket exceeds the maximum allowed count.
     * @throws ProductDoesNotExistInActiveProductsException If any product in the basket does not exist in the active products.
     */
    void validateBasketById(Long basketId);

    /**
     * Validates the RejectOrderRequest.
     *
     * @param orderId            The ID of the order to reject.
     * @param rejectOrderRequest The RejectOrderRequest to validate.
     * @throws OrderRequestIsNullException                If the orderId parameter is null.
     * @throws RejectOrderExceptionIsNullRequestException If the rejectOrderRequest parameter is null.
     * @throws AdminIdIsNullRequestException              If the adminId in the rejectOrderRequest is null.
     * @throws RejectOrderMessageIsNullRequestException   If the message in the rejectOrderRequest is null.
     */
    void validateOrderRejectRequest(Long orderId, RejectOrderRequest rejectOrderRequest);

    /**
     * Validates the UpdateOrderRequest.
     *
     * @param updateOrderRequest The UpdateOrderRequest to validate.
     * @throws UpdateOrderRequestIsNullException            If the updateOrderRequest parameter is null.
     * @throws OrderNotFoundException                       If the order with the specified ID does not exist.
     * @throws OrderTotalPriceIsNullException               If the total price in the updateOrderRequest is null.
     * @throws OrderTotalPriceInvalidException              If the total price in the updateOrderRequest is negative.
     * @throws OrderRequiredDoneTimeIsNullException         If the requiredDoneTime in the updateOrderRequest is null.
     * @throws OrderProductListIsNullException              If the product list in the updateOrderRequest is null.
     * @throws OrderProductListIsEmptyException             If the product list in the updateOrderRequest is empty.
     * @throws OrderProductCountIsNullException             If the count of any order product in the list is null.
     * @throws OrderProductVersionIsNullException           If the product version id of any order product in the list is null.
     * @throws OrderProductCountExceededException           If the count of any order product exceeds the maximum allowed count.
     * @throws ProductDoesNotExistInActiveProductsException If the product version id of any order product does not exist in the active products.
     */
    void validateUpdateOrderRequest(UpdateOrderRequest updateOrderRequest);

    /**
     * Validates the UpdateProcessStatusRequest.
     *
     * @param processStatus The UpdateProcessStatusRequest to validate.
     * @throws UpdateProcessStatusIsNullRequestException If the processStatus parameter is null.
     */
    void validateUpdateOrderStatusRequest(UpdateProcessStatusRequest processStatus);
}

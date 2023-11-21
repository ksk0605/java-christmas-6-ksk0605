package christmas.domain.discount;

import christmas.domain.order.Order;

import static christmas.constants.EventConstants.MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT;

public interface Discount {
    public int calculateDiscountAmount(Order order);

    public String getDiscountName();

    default boolean isNotSatisfiedMinimumAmount(Order order) {
        return order.calculateOrderAmount() < MINIMUM_ORDER_AMOUNT_FOR_DISCOUNT;
    }
}

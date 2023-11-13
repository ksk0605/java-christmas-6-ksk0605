package christmas.domain.discount;

import christmas.domain.order.Order;

public interface Discount {
    public int calculateDiscountAmount(Order order);

    public String getDiscountName();

    default boolean isNotSatisfiedMinimumAmount(Order order) {
        return order.calculateOrderAmount() < 10000;
    }
}

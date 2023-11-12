package christmas.domain.discount;

import christmas.domain.order.Order;

public interface Discount {
    public int calculateDiscountAmount(Order order);
}

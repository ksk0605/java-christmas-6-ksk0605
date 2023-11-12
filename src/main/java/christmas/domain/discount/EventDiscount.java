package christmas.domain.discount;

import christmas.domain.order.Order;

public class EventDiscount implements Discount {

    @Override
    public int calculateDiscountAmount(Order order) {
        if (order.calculateOrderAmount() < 120000) {
            return 0;
        }
        return 25000; // TODO : static 상수 분리
    }
}

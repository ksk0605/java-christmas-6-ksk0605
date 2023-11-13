package christmas.domain.discount;

import christmas.domain.order.Order;

public class EventDiscount implements Discount {

    private static boolean isNotEligibleForEvent(Order order) {
        return order.calculateOrderAmount() < 120000;
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        if (isNotSatisfiedMinimumAmount(order) || isNotEligibleForEvent(order)) {
            return 0;
        }
        return 25000; // TODO : static 상수 분리
    }

    @Override
    public String getDiscountName() {
        return "증정 이벤트";
    }
}

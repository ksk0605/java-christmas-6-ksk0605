package christmas.domain.discount;

import christmas.domain.event.EventItem;
import christmas.domain.order.Order;

import static christmas.constants.EventConstants.*;

public class EventDiscount implements Discount {

    private static boolean isNotEligibleForEvent(Order order) {
        return order.calculateOrderAmount() < EVENT_MINIMUM_DISCOUNT_AMOUNT;
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        if (isNotSatisfiedMinimumAmount(order) || isNotEligibleForEvent(order)) {
            return ZERO_DISCOUNT;
        }
        return EventItem.샴페인.getItemPrice();
    }

    @Override
    public String getDiscountName() {
        return EVENT_DISCOUNT_NAME;
    }
}

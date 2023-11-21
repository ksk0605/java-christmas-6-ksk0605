package christmas.domain.discount;

import christmas.domain.order.Order;

import static christmas.constants.EventConstants.*;

public class ChristmasDiscount implements Discount {
    private static boolean isNotDiscountDate(int visitDate) {
        return visitDate > CHRISTMAS_DISCOUNT_DAY_THRESHOLD;
    }

    private static int calculateAmount(int visitDate) {
        int daysSinceFirstDay = visitDate - 1;
        return CHRISTMAS_MINIMUM_DISCOUNT_AMOUNT + (CHRISTMAS_DISCOUNT_PER_DAY * (daysSinceFirstDay));
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (isNotSatisfiedMinimumAmount(order) || isNotDiscountDate(visitDate)) {
            return ZERO_DISCOUNT;
        }

        return calculateAmount(visitDate);
    }

    @Override
    public String getDiscountName() {
        return CHRISTMAS_DISCOUNT_NAME;
    }
}
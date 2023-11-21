package christmas.domain.discount;

import christmas.domain.order.Order;

import static christmas.constants.EventConstants.*;

public class SpecialDiscount implements Discount {

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();
        if (isNotSatisfiedMinimumAmount(order) || isNotSpecialDiscountDay(visitDate)) {
            return ZERO_DISCOUNT;
        }
        return SPECIAL_MINIMUM_DISCOUNT_AMOUNT;
    }

    @Override
    public String getDiscountName() {
        return SPECIAL_DISCOUNT_NAME;
    }

    private static boolean isNotSpecialDiscountDay(int visitDate) {
        return !SpecialDiscountDay.isSpecialDiscountDay(visitDate);
    }
}

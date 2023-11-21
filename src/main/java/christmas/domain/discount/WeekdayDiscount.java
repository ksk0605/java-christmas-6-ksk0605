package christmas.domain.discount;

import christmas.domain.order.Order;
import christmas.util.WeekdayWeekendChecker;

import static christmas.constants.EventConstants.*;

public class WeekdayDiscount implements Discount {

    private static boolean isWeekend(int visitDate) {
        return WeekdayWeekendChecker.isWeekend(visitDate);
    }

    private static int calculateAmount(Order order) {
        return WEEKDAY_DISCOUNT_PER_ORDER_ITEM * order.calculateDessertOrderItemCountAmount();
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (isNotSatisfiedMinimumAmount(order) || isWeekend(visitDate)) {
            return ZERO_DISCOUNT;
        }

        return calculateAmount(order);
    }

    @Override
    public String getDiscountName() {
        return WEEKDAY_DISCOUNT_NAME;
    }
}

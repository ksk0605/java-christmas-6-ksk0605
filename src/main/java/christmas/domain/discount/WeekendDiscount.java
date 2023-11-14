package christmas.domain.discount;

import christmas.domain.order.Order;
import christmas.util.WeekdayWeekendChecker;

import static christmas.constants.EventConstants.*;

public class WeekendDiscount implements Discount {

    private static boolean isWeekday(int visitDate) {
        return WeekdayWeekendChecker.isWeekday(visitDate);
    }

    private static int calculateAmount(Order order) {
        return WEEKEND_DISCOUNT_PER_ORDER_ITEM * order.getMainOrderItemCountAmount();
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (isNotSatisfiedMinimumAmount(order) || isWeekday(visitDate)) {
            return ZERO_DISCOUNT;
        }

        return calculateAmount(order);
    }

    @Override
    public String getDiscountName() {
        return WEEKEND_DISCOUNT_NAME;
    }
}

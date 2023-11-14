package christmas.domain.discount;

import christmas.domain.order.Order;
import christmas.util.WeekdayWeekendChecker;

public class WeekendDiscount implements Discount {

    private static boolean isWeekday(int visitDate) {
        return WeekdayWeekendChecker.isWeekday(visitDate);
    }

    private static int calculateAmount(Order order) {
        return 2023 * order.getMainOrderItemCountAmount();
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (isNotSatisfiedMinimumAmount(order) || isWeekday(visitDate)) {
            return 0;
        }

        return calculateAmount(order);
    }

    @Override
    public String getDiscountName() {
        return "주말 할인";
    }
}

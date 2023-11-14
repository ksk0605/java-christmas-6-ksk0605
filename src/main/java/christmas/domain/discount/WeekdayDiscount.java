package christmas.domain.discount;

import christmas.domain.order.Order;
import christmas.util.WeekdayWeekendChecker;

public class WeekdayDiscount implements Discount {

    private static boolean isWeekend(int visitDate) {
        return WeekdayWeekendChecker.isWeekend(visitDate);
    }

    private static int calculateAmount(Order order) {
        return 2023 * order.getDessertOrderItemCountAmount();
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (isNotSatisfiedMinimumAmount(order) || isWeekend(visitDate)) {
            return 0;
        }

        return calculateAmount(order);
    }

    @Override
    public String getDiscountName() {
        return "평일 할인";
    }
}

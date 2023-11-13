package christmas.domain.discount;

import christmas.domain.order.Order;

public class WeekdayDiscount implements Discount {

    private static boolean isNotWeekDayDate(int visitDate) {
        return visitDate % 7 == 1 || visitDate % 7 == 2;
    }

    private static int calculateAmount(Order order) {
        return 2023 * order.getDessertOrderItemCountAmount();
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (isNotSatisfiedMinimumAmount(order) || isNotWeekDayDate(visitDate)) {
            return 0;
        }

        return calculateAmount(order);
    }

    @Override
    public String getDiscountName() {
        return "평일 할인";
    }
}

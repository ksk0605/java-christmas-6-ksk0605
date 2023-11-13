package christmas.domain.discount;

import christmas.domain.order.Order;

public class WeekendDiscount implements Discount {

    private static boolean isNotWeekendDate(int visitDate) {
        return !(visitDate % 7 == 1 || visitDate % 7 == 2);
    }

    private static int calculateAmount(Order order) {
        return 2023 * order.getMainOrderItemCountAmount();
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (isNotSatisfiedMinimumAmount(order) || isNotWeekendDate(visitDate)) {
            return 0;
        }

        return calculateAmount(order);
    }

    @Override
    public String getDiscountName() {
        return "주말 할인";
    }
}

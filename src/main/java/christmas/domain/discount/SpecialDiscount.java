package christmas.domain.discount;

import christmas.domain.order.Order;

public class SpecialDiscount implements Discount {

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();
        if (isNotSatisfiedMinimumAmount(order) || isNotSpecialDiscountDay(visitDate)) {
            return 0;
        }
        return 1000; // TODO : static 상수 분리
    }

    private static boolean isNotSpecialDiscountDay(int visitDate) {
        return !SpecialDiscountDay.isSpecialDiscountDay(visitDate);
    }
}

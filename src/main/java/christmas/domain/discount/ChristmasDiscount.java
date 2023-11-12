package christmas.domain.discount;

import christmas.domain.order.Order;

public class ChristmasDiscount implements Discount {
    private static boolean isNotDiscountDate(int visitDate) {
        return visitDate > 25;
    }

    private static int calculateAmount(int visitDate) {
        return 1000 + (100 * (visitDate - 1));
    }

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (isNotDiscountDate(visitDate)) {
            return 0;
        }

        return calculateAmount(visitDate);
    }
}
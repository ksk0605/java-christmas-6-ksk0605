package christmas.domain.discount;

import christmas.domain.order.Order;

public class ChristmasDiscount implements Discount {
    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (visitDate > 25) {
            return 0;
        }

        return 1000 + (100 * (visitDate - 1));
    }
}
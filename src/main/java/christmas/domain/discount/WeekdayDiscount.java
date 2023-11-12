package christmas.domain.discount;

import christmas.domain.order.Order;

public class WeekdayDiscount implements Discount {

    @Override
    public int calculateDiscountAmount(Order order) {
        int visitDate = order.getVisitDate();

        if (visitDate % 7 != 1 && visitDate % 7 != 2) {
            return 0;
        }

        return 2023 * order.orderItemCounts();
    }
}

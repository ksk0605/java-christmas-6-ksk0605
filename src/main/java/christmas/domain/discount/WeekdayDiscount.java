package christmas.domain.discount;

import christmas.domain.order.Order;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount implements Discount {

    private static boolean isNotWeekDayDate(int visitDate) {
        LocalDate date = LocalDate.of(2023, 12, visitDate);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
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

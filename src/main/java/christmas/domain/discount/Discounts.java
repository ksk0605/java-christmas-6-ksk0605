package christmas.domain.discount;

import christmas.domain.order.Order;

import java.util.ArrayList;
import java.util.List;

import static christmas.constants.EventConstants.ZERO_DISCOUNT;

public class Discounts {
    private final List<Discount> discounts;

    public Discounts() {
        discounts = new ArrayList<>();
        createItems();
    }

    private void createItems() {
        discounts.add(new ChristmasDiscount());
        discounts.add(new WeekdayDiscount());
        discounts.add(new WeekendDiscount());
        discounts.add(new SpecialDiscount());
        discounts.add(new EventDiscount());
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public boolean allDiscountsZero(Order order) {
        return discounts.stream()
                .allMatch(discount -> discount.calculateDiscountAmount(order) == ZERO_DISCOUNT);
    }

    public int sumAllDiscounts(Order order) {
        int discountAmount = 0;
        for (Discount discount : discounts) {
            discountAmount += discount.calculateDiscountAmount(order);
        }
        return discountAmount;
    }

    public int sumAllDiscountAmountExcludingEventDiscount(Order order) {
        int discountAmount = 0;
        for (Discount discount : discounts) {
            if (isNotEventDiscount(discount)) {
                discountAmount -= discount.calculateDiscountAmount(order);
            }
        }
        return discountAmount;
    }

    private static boolean isNotEventDiscount(Discount discount) {
        return !(discount instanceof EventDiscount);
    }
}

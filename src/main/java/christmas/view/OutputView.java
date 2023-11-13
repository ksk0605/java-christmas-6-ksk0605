package christmas.view;

import christmas.domain.discount.Discount;
import christmas.domain.discount.Discounts;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventItem;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;

import java.text.NumberFormat;
import java.util.Optional;

import static christmas.view.ViewMessages.*;

public class OutputView {
    public static void printEventHeader() {
        System.out.println(EVENT_PREVIEW_HEADER);
    }

    public static void printMenu(Order order) {
        System.out.println(ORDER_MENU_HEADER);
        OrderItems orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems.getItems()) {
            System.out.println(orderItem.getMenuItem() + " " + orderItem.getMenuCount() + COUNT_SUFFIX);
        }
    }

    public static void printOrderAmount(Order order) {
        System.out.println(TOTAL_ORDER_AMOUNT_HEADER);
        System.out.println(formatPrizeAmount(order.calculateOrderAmount()) + WON_SUFFIX);
    }

    public static void printEventItem(Order order) {
        System.out.println(BONUS_MENU_HEADER);
        EventItem eventItem = EventItem.from(order.calculateOrderAmount());
        if (eventItem.equals(EventItem.없음)) {
            System.out.println(NO_DISCOUNT_MESSAGE);
            return;
        }
        System.out.println(eventItem + " " + eventItem.getItemCount() + COUNT_SUFFIX);
    }

    public static void printDiscountDetails(Discounts discounts, Order order) {
        System.out.println(DISCOUNT_DETAILS_HEADER);

        if (discounts.allDiscountsZero(order)) {
            System.out.println(NO_DISCOUNT_MESSAGE);
            return;
        }

        for (Discount discount : discounts.getDiscounts()) {
            int discountAmount = discount.calculateDiscountAmount(order);
            if (discountAmount == 0) {
                continue;
            }
            System.out.println(discount.getDiscountName() + COLON_SPACE + MINUS_SIGN + formatPrizeAmount(discountAmount) + WON_SUFFIX);
        }
    }

    public static void printDiscountAmount(Discounts discounts, Order order) {
        System.out.println(TOTAL_DISCOUNT_AMOUNT_HEADER);
        int discountAmount = discounts.sumAllDiscounts(order);
        if (discountAmount == 0) {
            System.out.println(discountAmount + WON_SUFFIX);
            return;
        }
        System.out.println(MINUS_SIGN + formatPrizeAmount(discountAmount) + WON_SUFFIX);
    }

    public static void printExpectedPaymentAmount(Discounts discounts, Order order) {
        System.out.println(FINAL_PAYMENT_AMOUNT_HEADER);
        int expectedPaymentAmount = order.calculateOrderAmount() + discounts.sumAllDiscountAmountExcludingEventDiscount(order);
        System.out.println(formatPrizeAmount(expectedPaymentAmount) + WON_SUFFIX);
    }

    public static void printEventBadge(Discounts discounts, Order order) {
        System.out.println(EVENT_BADGE_HEADER);
        int discountAmount = discounts.sumAllDiscounts(order);
        Optional<EventBadge> eventBadge = EventBadge.getBadgeForAmount(discountAmount);
        eventBadge.ifPresentOrElse(
                badge -> System.out.println(badge),
                () -> System.out.println("없음")
        );
    }

    private static String formatPrizeAmount(int amount) {
        return NumberFormat.getIntegerInstance().format(amount);
    }
}

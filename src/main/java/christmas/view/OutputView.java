package christmas.view;

import christmas.domain.EventBadge;
import christmas.domain.discount.Discount;
import christmas.domain.discount.Discounts;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;

import java.text.NumberFormat;

public class OutputView { // TODO : 리팩토링
    public static void printMenu(Order order) {
        System.out.println("\n<주문 메뉴>");
        OrderItems orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems.getItems()) {
            System.out.println(orderItem.getMenuItem() + " " + orderItem.getMenuCount() + "개");
        }
    }

    public static void printOrderAmount(Order order) {
        System.out.println("\n<할인 전 총주문 금액>\n" + formatPrizeAmount(order.calculateOrderAmount()) + "원");
    }

    public static void printEventItem(Order order) {
        System.out.println("\n<증정 메뉴>");
        String eventItem = createEventItem(order);
        System.out.println(eventItem);
    }

    public static void printDiscountDetails(Discounts discounts, Order order) {
        System.out.println("\n<혜택 내역>");

        if (discounts.allDiscountsZero(order)) {
            System.out.println("없음");
            return;
        }

        for (Discount discount : discounts.getDiscounts()) {
            int discountAmount = discount.calculateDiscountAmount(order);
            if (discountAmount == 0) {
                continue;
            }
            System.out.println(discount.getDiscountName() + ": -" + formatPrizeAmount(discountAmount) + "원");
        }
    }

    public static void printDiscountAmount(Discounts discounts, Order order) {
        System.out.println("\n<총혜택 금액>");
        int discountAmount = discounts.sumAllDiscounts(order);
        if (discountAmount == 0) {
            System.out.println("0원");
            return;
        }
        System.out.println("-" + formatPrizeAmount(discountAmount) + "원");
    }

    public static void printExpectedPaymentAmount(Discounts discounts, Order order) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        int expectedPaymentAmount = order.calculateOrderAmount() + discounts.sumAllDiscountAmountExcludingEventDiscount(order);
        System.out.println(formatPrizeAmount(expectedPaymentAmount) + "원");
    }

    public static void printEventBadge(Discounts discounts, Order order) {
        System.out.println("\n<12월 이벤트 배지>");
        int discountAmount = discounts.sumAllDiscounts(order);
        EventBadge eventBadge = EventBadge.getBadgeForAmount(discountAmount);
        if (eventBadge == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(eventBadge);
    }

    private static String createEventItem(Order order) { // TODO : 이벤트 아이템 enum으로 리팩토링
        if (order.calculateOrderAmount() < 120000) {
            return "없음";
        }
        return "샴페인 1개";
    }

    private static String formatPrizeAmount(int amount) {
        return NumberFormat.getIntegerInstance().format(amount);
    }
}

package christmas.view;

import christmas.domain.discount.Discount;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;

import java.text.NumberFormat;
import java.util.List;

public class OutputView {
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

    public static void printDiscountDetails(List<Discount> discounts, Order order) { // TODO : Discounts 클래스
        System.out.println("\n<혜택 내역>");

        if (hasNoDiscount(discounts, order)) {
            System.out.println("없음");
            return;
        }

        for (Discount discount : discounts) {
            int discountAmount = discount.calculateDiscountAmount(order);
            if (discountAmount == 0) {
                continue;
            }
            System.out.println(discount.getDiscountName() + ": -" + formatPrizeAmount(discountAmount) + "원");
        }
    }

    private static boolean hasNoDiscount(List<Discount> discounts, Order order) {
        boolean hasDiscount = discounts.stream()
                .anyMatch(discount -> discount.calculateDiscountAmount(order) > 0);
        return !hasDiscount;
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

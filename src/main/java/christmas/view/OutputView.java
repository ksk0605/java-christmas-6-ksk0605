package christmas.view;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;

import java.text.NumberFormat;

public class OutputView {
    public static void printMenu(Order order) {
        System.out.println("\n<주문 메뉴>");
        OrderItems orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems.getItems()) {
            System.out.println(orderItem.getMenuItem() + " " + orderItem.getMenuCount() + "개");
        }
    }

    public static void printOrderAmount(Order order) {
        System.out.println("\n<할인 전 총주문 금액>\n" + formatPrizeAmount(order) + "원");
    }

    public static void printEventItem(Order order) {
        System.out.println("\n<증정 메뉴>");
        String eventItem = createEventItem(order);
        System.out.println(eventItem);
    }

    private static String createEventItem(Order order) {
        if (order.calculateOrderAmount() < 120000) {
            return "없음";
        }
        return "샴페인 1개";
    }

    private static String formatPrizeAmount(Order order) {
        return NumberFormat.getIntegerInstance().format(order.calculateOrderAmount());
    }
}

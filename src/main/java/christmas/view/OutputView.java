package christmas.view;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;

public class OutputView {
    public static void printMenu(Order order) {
        System.out.println("\n<주문 메뉴>");
        OrderItems orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems.getItems()) {
            System.out.println(orderItem.getMenuItem() + " " + orderItem.getMenuCount() + "개");
        }
    }
}

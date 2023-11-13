package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.discount.Discounts;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {
    private final Discounts discounts;

    public EventPlanner(Discounts discounts) {
        this.discounts = discounts;
    }

    public void start() {
        VisitDate visitDate = createVisitDate();

        Order order = createOrder(visitDate);

        OutputView.printMenu(order);
        OutputView.printOrderAmount(order);
        OutputView.printEventItem(order);
        OutputView.printDiscountDetails(discounts, order);
        OutputView.printDiscountAmount(discounts, order);
        OutputView.printExpectedPaymentAmount(discounts, order);
        OutputView.printEventBadge(discounts, order);
    }

    private static Order createOrder(VisitDate visitDate) {
        while (true) {
            try {
                String[] orders = InputView.readOrder();
                OrderItems orderItems = new OrderItems(orders);

                return new Order(orderItems, visitDate);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static VisitDate createVisitDate() {
        while (true) {
            try {
                return InputView.readDate();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}

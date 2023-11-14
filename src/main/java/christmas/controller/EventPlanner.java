package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.discount.Discounts;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;

import static christmas.view.InputView.readDate;
import static christmas.view.InputView.readOrderItems;
import static christmas.view.OutputView.*;
import static christmas.view.StaticHeaderView.printEventHeader;
import static christmas.view.StaticHeaderView.printWelcomeMessage;

public class EventPlanner {
    private final Discounts discounts;

    public EventPlanner(Discounts discounts) {
        this.discounts = discounts;
    }

    public void start() {
        printWelcomeMessage();

        VisitDate visitDate = createVisitDate();
        Order order = createOrder(visitDate);

        printEventHeader(visitDate);
        printMenu(order);
        printOrderAmount(order);
        printEventItem(order);
        printDiscountDetails(discounts, order);
        printDiscountAmount(discounts, order);
        printExpectedPaymentAmount(discounts, order);
        printEventBadge(discounts, order);
    }

    private static Order createOrder(VisitDate visitDate) {
        while (true) {
            try {
                OrderItems orderItems = readOrderItems();
                return new Order(orderItems, visitDate);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static VisitDate createVisitDate() {
        while (true) {
            try {
                return readDate();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}

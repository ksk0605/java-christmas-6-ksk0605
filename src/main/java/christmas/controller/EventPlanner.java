package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.discount.Discounts;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import christmas.handler.InputHandler;
import christmas.view.InputView;

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
        OrderItems orderItems = InputHandler.createWithRetry(InputView::readOrderItems);
        return new Order(orderItems, visitDate);
    }

    private static VisitDate createVisitDate() {
        return InputHandler.createWithRetry(InputView::readDate);
    }
}

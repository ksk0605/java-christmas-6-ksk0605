package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
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
        // 웰컴 메시지 출력
        printWelcomeMessage();

        // 사용자 입력
        VisitDate visitDate = createVisitDate();
        Order order = createOrder(visitDate);

        // 결과 출력
        printEventHeader(visitDate);
        printResult(order);
    }

    private void printResult(Order order) {
        printMenu(order);
        printOrderAmount(order);
        printEventItem(order);
        printDiscountDetails(discounts, order);
        printDiscountAmount(discounts, order);
        printExpectedPaymentAmount(discounts, order);
        printEventBadge(discounts, order);
    }

    public void finish() {
        Console.close();
    }

    private Order createOrder(VisitDate visitDate) {
        OrderItems orderItems = InputHandler.createWithRetry(InputView::readOrderItems);
        return new Order(orderItems, visitDate);
    }

    private VisitDate createVisitDate() {
        return InputHandler.createWithRetry(InputView::readDate);
    }
}

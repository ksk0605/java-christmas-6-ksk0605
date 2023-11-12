package christmas.view;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class OutputViewTest {
    private static final int EXAMPLE_VISIT_DATE = 5;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    OrderItems orderItems;
    VisitDate visitDate;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        orderItems = new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1".split(","));
        visitDate = new VisitDate(EXAMPLE_VISIT_DATE);
    }

    @Test
    @DisplayName("주문한 메뉴를 출력한다.")
    void printMenu() {
        // then
        Order order = new Order(orderItems, visitDate);

        // when
        OutputView.printMenu(order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo("<주문 메뉴>\n" + "티본스테이크 1개\n바비큐립 1개\n초코케이크 2개\n제로콜라 1개");
    }

    @Test
    @DisplayName("할인 전 총주문 금액을 출력한다.")
    void printOrderAmount() {
        // then
        Order order = new Order(orderItems, visitDate);

        // when
        OutputView.printOrderAmount(order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo("<할인 전 총주문 금액>\n142,000원");
    }

    @Test
    @DisplayName("증정 메뉴를 출력한다.")
    void printEventItem() {
        // then
        Order order = new Order(orderItems, visitDate);

        // when
        OutputView.printEventItem(order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo("<증정 메뉴>\n샴페인 1개");
    }
}
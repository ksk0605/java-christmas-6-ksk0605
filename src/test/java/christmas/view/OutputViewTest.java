package christmas.view;

import christmas.domain.VisitDate;
import christmas.domain.discount.*;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class OutputViewTest {
    private static final int EXAMPLE_VISIT_DATE = 3;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    OrderItems orderItems;
    VisitDate visitDate;
    List<Discount> discounts = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        orderItems = new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1".split(","));
        visitDate = new VisitDate(EXAMPLE_VISIT_DATE);
        discounts.add(new ChristmasDiscount());
        discounts.add(new WeekdayDiscount());
        discounts.add(new WeekendDiscount());
        discounts.add(new SpecialDiscount());
        discounts.add(new EventDiscount());
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

    @Test // TODO : 없음 테스트
    @DisplayName("증정 메뉴를 출력한다.")
    void printEventItem() {
        // then TODO : given으로 수정
        Order order = new Order(orderItems, visitDate);

        // when
        OutputView.printEventItem(order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo("<증정 메뉴>\n샴페인 1개");
    }

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/" +
            "<혜택 내역>\n크리스마스 디데이 할인: -1,200원\n평일 할인: -4,046원\n특별 할인: -1,000원\n증정 이벤트: -25,000원",
            "타파스-1,제로콜라-1/" +
                    "<혜택 내역>\n없음"})
    @DisplayName("증정 메뉴를 출력한다.")
    void printDiscountDetails(String value) {
        // given
        // strings[0] 은 사용자 입력 strings[1] 은 출력결과
        String[] strings = value.split("/");
        OrderItems testOrderItems = new OrderItems(strings[0].split(","));
        Order order = new Order(testOrderItems, visitDate);

        // when
        OutputView.printDiscountDetails(discounts, order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo(strings[1]);
    }
}
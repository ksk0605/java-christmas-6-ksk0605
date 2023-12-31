package christmas.view;

import christmas.domain.VisitDate;
import christmas.domain.discount.Discounts;
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

class OutputViewTest {
    private static final String EXAMPLE_VISIT_DATE = "3";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    OrderItems orderItems;
    VisitDate visitDate;
    Discounts discounts;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        orderItems = new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        visitDate = new VisitDate(EXAMPLE_VISIT_DATE);
        discounts = new Discounts();
    }

    @Test
    @DisplayName("주문한 메뉴를 출력한다.")
    void printMenu() {
        // given
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
        // given
        Order order = new Order(orderItems, visitDate);

        // when
        OutputView.printOrderAmount(order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo("<할인 전 총주문 금액>\n142,000원");
    }

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/" +
            "<증정 메뉴>\n샴페인 1개",
            "타파스-1,제로콜라-1/" +
                    "<증정 메뉴>\n없음"})
    @DisplayName("증정 메뉴를 출력한다.")
    void printEventItem() {
        // given
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
    @DisplayName("혜택 내역를 출력한다.")
    void printDiscountDetails(String value) {
        // given
        // strings[0] 은 사용자 입력 strings[1] 은 출력결과
        String[] strings = value.split("/");
        OrderItems testOrderItems = new OrderItems(strings[0]);
        Order order = new Order(testOrderItems, visitDate);

        // when
        OutputView.printDiscountDetails(discounts, order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo(strings[1]);
    }

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/" +
            "<총혜택 금액>\n-31,246원",
            "타파스-1,제로콜라-1/" +
                    "<총혜택 금액>\n0원"})
    @DisplayName("총혜택 금액을 출력한다.")
    void printDiscountAmount(String value) {
        // given
        // strings[0] 은 사용자 입력 strings[1] 은 출력결과
        String[] strings = value.split("/");
        OrderItems testOrderItems = new OrderItems(strings[0]);
        Order order = new Order(testOrderItems, visitDate);

        // when
        OutputView.printDiscountAmount(discounts, order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo(strings[1]);
    }

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/" +
            "<할인 후 예상 결제 금액>\n135,754원",
            "타파스-1,제로콜라-1/" +
                    "<할인 후 예상 결제 금액>\n8,500원"})
    @DisplayName("할인 후 예상 결제 금액을 출력한다.")
    void printExpectedPaymentAmount(String value) {
        // given
        // strings[0] 은 사용자 입력 strings[1] 은 출력결과
        String[] strings = value.split("/");
        OrderItems testOrderItems = new OrderItems(strings[0]);
        Order order = new Order(testOrderItems, visitDate);

        // when
        OutputView.printExpectedPaymentAmount(discounts, order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo(strings[1]);
    }

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1/" +
            "<12월 이벤트 배지>\n산타",
            "타파스-1,제로콜라-1/" +
                    "<12월 이벤트 배지>\n없음"})
    @DisplayName("12월 이벤트 배지를 출력한다.")
    void printEventBadge(String value) {
        // given
        // strings[0] 은 사용자 입력 strings[1] 은 출력결과
        String[] strings = value.split("/");
        OrderItems testOrderItems = new OrderItems(strings[0]);
        Order order = new Order(testOrderItems, visitDate);

        // when
        OutputView.printEventBadge(discounts, order);

        // then
        String capturedOutput = outputStreamCaptor.toString().trim();
        Assertions.assertThat(capturedOutput).isEqualTo(strings[1]);
    }
}
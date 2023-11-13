package christmas.domain.order;

import christmas.domain.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    private static final String EXAMPLE_VISIT_DATE = "4";
    VisitDate visitDate;

    @BeforeEach
    void setUp() {
        visitDate = new VisitDate(EXAMPLE_VISIT_DATE);
    }

    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:142000", "타파스-1,제로콜라-1:8500"}, delimiter = ':')
    @DisplayName("총 주문 금액을 계산한다.")
    void calculateOrderAmount(String orderInput, int amount) {
        // given
        OrderItems orderItems = new OrderItems(orderInput.split(","));
        Order order = new Order(orderItems, visitDate);

        // when
        int orderAmount = order.calculateOrderAmount();

        // then
        assertThat(orderAmount).isEqualTo(amount);
    }
}
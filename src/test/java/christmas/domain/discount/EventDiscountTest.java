package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class EventDiscountTest {
    private static final String EXAMPLE_VISIT_DATE = "5";
    EventDiscount eventDiscount;
    VisitDate visitDate;

    @BeforeEach
    void setUp() {
        eventDiscount = new EventDiscount();
        visitDate = new VisitDate(EXAMPLE_VISIT_DATE);
    }

    @DisplayName("이벤트 혜택 할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:25000", "타파스-1,제로콜라-1:0"}, delimiter = ':')
    void calculateDiscountAmount(String orderInput, int amount) {
        // given
        OrderItems orderItems = new OrderItems(orderInput);
        Order order = new Order(orderItems, visitDate);

        // when
        int discountAmount = eventDiscount.calculateDiscountAmount(order);

        // then
        assertThat(discountAmount).isEqualTo(amount);
    }
}
package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountTest {
    SpecialDiscount specialDiscount;
    OrderItems orderItems;

    @BeforeEach
    void setUp() {
        specialDiscount = new SpecialDiscount();
        orderItems = new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1".split(","));
    }

    @DisplayName("특별 할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"3:1000", "5:0"}, delimiter = ':')
    void calculateDiscountAmount(int date, int amount) {
        // given
        VisitDate visitDate = new VisitDate(date);
        Order order = new Order(orderItems, visitDate);

        // when
        int discountAmount = specialDiscount.calculateDiscountAmount(order);

        // then
        assertThat(discountAmount).isEqualTo(amount);
    }
}
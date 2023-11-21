package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class WeekdayDiscountTest {
    WeekdayDiscount weekdayDiscount = new WeekdayDiscount();

    @DisplayName("평일 할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"샴페인-1,초코케이크-2:1:0", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1:3:4046"}, delimiter = ':')
    void calculateDiscountAmount(String input, String date, int amount) {
        // given
        VisitDate visitDate = new VisitDate(date);
        OrderItems orderItems = new OrderItems(input);
        Order order = new Order(orderItems, visitDate);

        // when
        int discountAmount = weekdayDiscount.calculateDiscountAmount(order);

        // then
        assertThat(discountAmount).isEqualTo(amount);
    }
}
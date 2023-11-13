package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDiscountTest {
    ChristmasDiscount christmasDiscount = new ChristmasDiscount();

    @DisplayName("크리스마스 디데이 할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1000", "15,2400", "25,3400"})
    void calculateDiscountAmount(String date, int amount) {
        // given
        VisitDate visitDate = new VisitDate(date);
        OrderItems orderItems = new OrderItems(new String[]{"샴페인-1", "초코케이크-2"});
        Order order = new Order(orderItems, visitDate);

        // when
        int discountAmount = christmasDiscount.calculateDiscountAmount(order);

        // then
        assertThat(discountAmount).isEqualTo(amount);
    }
}
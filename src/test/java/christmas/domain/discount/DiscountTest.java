package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountTest {
    private static final int EXAMPLE_VISIT_DATE = 5;
    private static final String[] EXAMPLE_ORDER_ITEM = "타파스-1,제로콜라-1".split(",");
    List<Discount> discounts;
    VisitDate visitDate;
    OrderItems orderItems;

    @BeforeEach
    void setUp() {
        discounts = new ArrayList<>();
        discounts.add(new ChristmasDiscount());
        discounts.add(new EventDiscount());
        discounts.add(new WeekdayDiscount());
        discounts.add(new WeekendDiscount());
        discounts.add(new SpecialDiscount());
        visitDate = new VisitDate(EXAMPLE_VISIT_DATE);
        orderItems = new OrderItems(EXAMPLE_ORDER_ITEM);
    }

    @Test
    @DisplayName("총주문 금액 10,000원 이상부터 이벤트가 적용된다.")
    void isNotSatisfiedMinimumAmount() {
        // given
        Order order = new Order(orderItems, visitDate);
        int discountAmount = 0;

        // when
        for (Discount discount : discounts) {
            discountAmount += discount.calculateDiscountAmount(order);
        }

        // then
        assertThat(discountAmount).isEqualTo(0);
    }
}
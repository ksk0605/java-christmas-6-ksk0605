package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderItemsTest {
    @Test
    @DisplayName("메뉴는 중복할 수 없다.")
    void createOrderItemsWithDuplicateValues() {
        assertThatThrownBy(() ->
                new OrderItems(new String[]{"샴페인-1", "샴페인-2"})
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("예시와 다른 메뉴 형식은 입력할 수 없다.")
    void createOrderItemsWithInvalidInputs() {
        assertThatThrownBy(() ->
                new OrderItems(new String[]{"양송이수프(1)"})
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴의 개수는 1이상의 정수만 입력할 수 있다.")
    void createOrderItemsWithInvalidIntegers() {
        assertThatThrownBy(() ->
                new OrderItems(new String[]{"양송이수프-0"})
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴는 주문할 수 없다.")
    void createOrderItemsWithMenuNotRegistered() {
        assertThatThrownBy(() ->
                new OrderItems(new String[]{"제육볶음-2"})
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음료만 주문할 수 없다.")
    void createOrderItemsWithOnlyDrinks() {
        assertThatThrownBy(() ->
                new OrderItems(new String[]{"제로콜라-2", "레드와인-1"})
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
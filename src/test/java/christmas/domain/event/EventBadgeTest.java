package christmas.domain.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EventBadgeTest {
    @DisplayName("총 혜택 금액에 따라 다른 이벤트 배지를 부여한다.")
    @ParameterizedTest
    @CsvSource({
            "5000, 별",
            "10000, 트리",
            "20000, 산타",
            "6000, 별",
            "15000, 트리"
    })
    public void getBadgeForAmount(int amount, EventBadge expectedBadge) {
        assertEquals(expectedBadge, EventBadge.getBadgeForAmount(amount));
    }

    @DisplayName("총 할인 금액이 5000원 미만 일 경우에는 배지를 지급하지 않는다.")
    @Test
    public void getBadgeForAmountWithBelowMinimum() {
        assertNull(EventBadge.getBadgeForAmount(4999));
    }
}
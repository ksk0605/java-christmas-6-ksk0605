package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDateTest {
    @ValueSource(ints = {-1, 0, 32})
    @ParameterizedTest
    @DisplayName("날짜는 1이상 31 이하의 숫자만 입력할 수 있다.")
    void createVisitDateWithOutOfRangeInteger(int date) {
        assertThatThrownBy(() ->
                new VisitDate(date)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
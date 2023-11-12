package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {
    @BeforeEach
    void resetBuffer() {
        Console.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"팔", "2,1", "@##@$"})
    @DisplayName("날짜는 하나의 정수만 입력할 수 있다.")
    void readDateWithInvalidInput(String input) {
        setInput(input);
        assertThatThrownBy(() -> InputView.readDate())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,제로콜라-1,,", ",,타파스-1,제로콜라-1", "타파스-1,,제로콜라-1"})
    @DisplayName("예시와 다른 메뉴 형식은 입력할 수 없다.")
    void readOrderWithInvalidInput(String input) {
        setInput(input);
        assertThatThrownBy(() -> InputView.readOrder())
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void setInput(String input) {
        final byte[] buf = input.getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }
}
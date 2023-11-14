package christmas;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "26:타파스-1,제로콜라-1:<혜택 내역>:없음",
            "26:타파스-1,제로콜라-1:<증정 메뉴>:없음",
            "26:타파스-1,제로콜라-1:<12월 이벤트 배지>:없음",
    }, delimiter = ':')
    void 결과_없음_출력(String date, String order, String outputTitle, String result) {
        assertSimpleTest(() -> {
            run(date, order);
            assertThat(output()).contains(outputTitle + LINE_SEPARATOR + result);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "-1", "0", "32", "%"})
    void 날짜_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "3:제로콜라-a:[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.",
            "3:제로콜라-0:[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.",
            "3:제로콜라-21:[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.",
            "3:제로콜라--2:[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.",
            "3:제로콜라/2:[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    }, delimiter = ':')
    void 주문_예외_테스트(String date, String order, String errorMessage) {
        assertSimpleTest(() -> {
            runException(date, order);
            assertThat(output()).contains(errorMessage);
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}

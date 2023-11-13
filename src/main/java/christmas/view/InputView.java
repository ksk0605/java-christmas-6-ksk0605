package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitDate;

import static christmas.view.ViewMessages.*;

public class InputView {
    public static VisitDate readDate() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(ENTER_VISIT_DATE_PROMPT);
        String input = createUserInput();
        return new VisitDate(input);
    }

    public static String[] readOrder() {
        System.out.println(ORDER_MENU_PROMPT);
        String input = createUserInput();
        validateInputOrder(input);
        return split(input);
    }

    private static String[] split(String input) {
        return input.split(",");
    }

    private static void validateInputOrder(String input) {
        String[] values = split(input);
        if (hasDuplicateComma(input) || isInvalidInputOrder(values)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean hasDuplicateComma(String input) {
        return input.contains(",,");
    }

    private static boolean isInvalidInputOrder(String[] values) {
        for (String value : values) {
            if (value.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static String createUserInput() {
        return Console.readLine();
    }
}

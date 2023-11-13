package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = getUserInput();
        validateInputDate(input);
        return toInt(input);
    }

    public static String[] readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = getUserInput();
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

    private static String getUserInput() {
        return Console.readLine();
    }

    private static int toInt(String input) {
        return Integer.parseInt(input);
    }

    private static void validateInputDate(String input) {
        if (isNotInteger(input)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean isNotInteger(String input) {
        try {
            toInt(input);
            return false;
        } catch (NumberFormatException exception) {
            return true;
        }
    }
}

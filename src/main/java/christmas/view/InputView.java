package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = getUserInputDate();
        validateInput(input);
        return toInt(input);
    }

    private static String getUserInputDate() {
        return Console.readLine();
    }

    private static int toInt(String input) {
        return Integer.parseInt(input);
    }

    private static void validateInput(String input) {
        if (isNotInteger(input)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean isNotInteger(String input) {
        try {
            toInt(input);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
